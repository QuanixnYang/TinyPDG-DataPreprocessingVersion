public class A{
    public static void main(String[] args) {
        Option optHelp = new Option("h", "help", false, "print this message");
        Option optCerts = new Option("c", "cert", true, "use external semicolon separated X.509 certificate files");
        optCerts.setArgName("certificates");
        Option optPasswd = new Option("p", "password", true, "set password for opening PDF");
        optPasswd.setArgName("password");
        Option optExtract = new Option("e", "extract", true, "extract signed PDF revisions to given folder");
        optExtract.setArgName("folder");
        Option optListKs = new Option("lk", "list-keystore-types", false, "list keystore types provided by java");
        Option optListCert = new Option("lc", "list-certificates", false, "list certificate aliases in a KeyStore");
        Option optKsType = new Option("kt", "keystore-type", true, "use keystore type with given name");
        optKsType.setArgName("keystore_type");
        Option optKsFile = new Option("kf", "keystore-file", true, "use given keystore file");
        optKsFile.setArgName("file");
        Option optKsPass = new Option("kp", "keystore-password", true, "password for keystore file (look on -kf option)");
        optKsPass.setArgName("password");
        Option optFailFast = new Option("ff", "fail-fast", true, "flag which sets the Verifier to exit with error code on the first validation failure");
        final Options options = new Options();
        options.addOption(optHelp);
        options.addOption(optCerts);
        options.addOption(optPasswd);
        options.addOption(optExtract);
        options.addOption(optListKs);
        options.addOption(optListCert);
        options.addOption(optKsType);
        options.addOption(optKsFile);
        options.addOption(optKsPass);
        options.addOption(optFailFast);
        CommandLine line = null;
        try {
            CommandLineParser parser = new PosixParser();
            line = parser.parse(options, args);
        } catch (ParseException exp) {
            System.err.println("Illegal command used: " + exp.getMessage());
            System.exit(-1);
        }
        final boolean failFast = line.hasOption("ff");
        final String[] tmpArgs = line.getArgs();
        if (line.hasOption("h") || args == null || args.length == 0) {
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp(70, "java -jar Verifier.jar [file1.pdf [file2.pdf ...]]", "JSignPdf Verifier is a command line tool for verifying signed PDF documents.", options, null, true);
        } else if (line.hasOption("lk")) {
            for (String tmpKsType : KeyStoreUtils.getKeyStores()) {
                System.out.println(tmpKsType);
            }
        } else if (line.hasOption("lc")) {
            for (String tmpCert : KeyStoreUtils.getCertAliases(line.getOptionValue("kt"), line.getOptionValue("kf"), line.getOptionValue("kp"))) {
                System.out.println(tmpCert);
            }
        } else {
            final VerifierLogic tmpLogic = new VerifierLogic(line.getOptionValue("kt"), line.getOptionValue("kf"), line.getOptionValue("kp"));
            tmpLogic.setFailFast(failFast);
            if (line.hasOption("c")) {
                String tmpCertFiles = line.getOptionValue("c");
                for (String tmpCFile : tmpCertFiles.split(";")) {
                    tmpLogic.addX509CertFile(tmpCFile);
                }
            }
            byte[] tmpPasswd = null;
            if (line.hasOption("p")) {
                tmpPasswd = line.getOptionValue("p").getBytes();
            }
            String tmpExtractDir = null;
            if (line.hasOption("e")) {
                tmpExtractDir = new File(line.getOptionValue("e")).getPath();
            }
            for (String tmpFilePath : tmpArgs) {
                System.out.println("Verifying " + tmpFilePath);
                final File tmpFile = new File(tmpFilePath);
                if (!tmpFile.canRead()) {
                    System.err.println("Couln't read the file. Check the path and permissions.");
                    if (failFast) {
                        System.exit(-1);
                    }
                    continue;
                }
                final VerificationResult tmpResult = tmpLogic.verify(tmpFilePath, tmpPasswd);
                if (tmpResult.getException() != null) {
                    tmpResult.getException().printStackTrace();
                    System.exit(-1);
                } else {
                    System.out.println("Total revisions: " + tmpResult.getTotalRevisions());
                    for (SignatureVerification tmpSigVer : tmpResult.getVerifications()) {
                        System.out.println(tmpSigVer.toString());
                        if (tmpExtractDir != null) {
                            try {
                                File tmpExFile = new File(tmpExtractDir + "/" + tmpFile.getName() + "_" + tmpSigVer.getRevision() + ".pdf");
                                System.out.println("Extracting to " + tmpExFile.getCanonicalPath());
                                FileOutputStream tmpFOS = new FileOutputStream(tmpExFile.getCanonicalPath());
                                InputStream tmpIS = tmpLogic.extractRevision(tmpFilePath, tmpPasswd, tmpSigVer.getName());
                                IOUtils.copy(tmpIS, tmpFOS);
                                tmpIS.close();
                                tmpFOS.close();
                            } catch (IOException ioe) {
                                ioe.printStackTrace();
                            }
                        }
                    }
                    if (failFast && SignatureVerification.isError(tmpResult.getVerificationResultCode())) {
                        System.exit(tmpResult.getVerificationResultCode());
                    }
                }
            }
        }
    }
}