public class A{
        @Nullable
        @Override
        public InputSource resolveEntity(final String publicId, final String systemId) throws IOException {
            if (systemId.endsWith(".xml")) {
                return null;
            }
            InputSource inputSource = null;
            final URL url = IOUtils.getResource(new File("system/dtd"), PATTERN_DIRECTORY_PART.matcher(systemId).replaceAll(""));
            final InputStream inputStream = url.openStream();
            try {
                final BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
                try {
                    inputSource = new InputSource(bufferedInputStream);
                } finally {
                    if (inputSource == null) {
                        bufferedInputStream.close();
                    }
                }
            } finally {
                if (inputSource == null) {
                    inputStream.close();
                }
            }
            return inputSource;
        }
}