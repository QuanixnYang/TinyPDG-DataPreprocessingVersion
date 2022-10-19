public class A{
    private void createJCoPluginProject(IProgressMonitor monitor, String sourceFileName, String pluginName) throws CoreException, IOException {
        monitor.subTask(MessageFormat.format(Messages.ProjectGenerator_CreatePluginTaskDescription, pluginName));
        final Map<String, byte[]> files = readArchiveFile(sourceFileName);
        monitor.worked(10);
        IProject project = workspaceRoot.getProject(pluginName);
        if (project.exists()) {
            project.delete(true, true, new SubProgressMonitor(monitor, 5));
        } else {
            monitor.worked(5);
        }
        project.create(new SubProgressMonitor(monitor, 5));
        project.open(new SubProgressMonitor(monitor, 5));
        IProjectDescription description = project.getDescription();
        description.setNatureIds(new String[] { JavaCore.NATURE_ID, PLUGIN_NATURE_ID });
        project.setDescription(description, new SubProgressMonitor(monitor, 5));
        IJavaProject javaProject = JavaCore.create(project);
        IFolder binDir = project.getFolder("bin");
        IPath binPath = binDir.getFullPath();
        javaProject.setOutputLocation(binPath, new SubProgressMonitor(monitor, 5));
        project.getFolder("jni").create(true, true, new SubProgressMonitor(monitor, 5));
        project.getFile("sapjco3.jar").create(new ByteArrayInputStream(files.get("sapjco3.jar")), true, new SubProgressMonitor(monitor, 10));
        IFolder metaInfFolder = project.getFolder("META-INF");
        metaInfFolder.create(true, true, new SubProgressMonitor(monitor, 5));
        StringBuilder manifest = new StringBuilder();
        manifest.append("Manifest-Version: 1.0\n");
        manifest.append("Bundle-ManifestVersion: 2\n");
        manifest.append("Bundle-Name: SAP Java Connector v3\n");
        manifest.append(MessageFormat.format("Bundle-SymbolicName: {0}\n", pluginName));
        manifest.append("Bundle-Version: 7.11.0\n");
        manifest.append("Bundle-ClassPath: bin/,\n");
        manifest.append(" sapjco3.jar,\n");
        manifest.append(" jni/\n");
        manifest.append("Bundle-Vendor: SAP AG, Walldorf (packaged using RCER)\n");
        manifest.append("Bundle-RequiredExecutionEnvironment: J2SE-1.5\n");
        manifest.append("Export-Package: com.sap.conn.jco,\n");
        manifest.append(" com.sap.conn.jco.ext,\n");
        manifest.append(" com.sap.conn.jco.monitor,\n");
        manifest.append(" com.sap.conn.jco.rt,\n");
        manifest.append(" com.sap.conn.jco.server\n");
        manifest.append("Bundle-ActivationPolicy: lazy\n");
        writeTextFile(monitor, manifest, metaInfFolder.getFile("MANIFEST.MF"));
        final IPath jcoPath = new Path(MessageFormat.format("/{0}/sapjco3.jar", pluginName));
        IClasspathEntry jcoEntry = JavaCore.newLibraryEntry(jcoPath, Path.EMPTY, Path.EMPTY, true);
        final IPath jniPath = new Path(MessageFormat.format("/{0}/jni", pluginName));
        IClasspathEntry jniEntry = JavaCore.newLibraryEntry(jniPath, Path.EMPTY, Path.EMPTY, true);
        javaProject.setRawClasspath(new IClasspathEntry[] { jcoEntry, jniEntry }, new SubProgressMonitor(monitor, 5));
        StringBuilder buildProperties = new StringBuilder();
        buildProperties.append("bin.includes = META-INF/,\\\n");
        buildProperties.append("               sapjco3.jar,\\\n");
        buildProperties.append("               jni/,\\\n");
        buildProperties.append("               .\n");
        writeTextFile(monitor, buildProperties, project.getFile("build.properties"));
        exportableBundles.add(modelManager.findModel(project));
    }
}