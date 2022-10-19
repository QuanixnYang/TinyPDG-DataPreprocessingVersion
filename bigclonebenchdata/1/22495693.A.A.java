public class A{
    public byte[] loadClassFirst(final String className) {
        if (className.equals("com.sun.sgs.impl.kernel.KernelContext")) {
            final URL url = Thread.currentThread().getContextClassLoader().getResource("com/sun/sgs/impl/kernel/KernelContext.0.9.7.class.bin");
            if (url != null) {
                try {
                    return StreamUtil.read(url.openStream());
                } catch (IOException e) {
                }
            }
            throw new IllegalStateException("Unable to load KernelContext.0.9.7.class.bin");
        }
        return null;
    }
}