public class A{
    public static void main(String[] argv) throws IOException {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        ;
        try {
            session.beginTransaction();
            Properties cfg = new Properties();
            URL url = SVM.class.getClassLoader().getResource(CFG_FILE);
            cfg.load(url.openStream());
            int runMode = Integer.valueOf(cfg.getProperty(KEY_RUN_MODE));
            switch(runMode) {
                case RUN_OPT:
                    new SVM().optimizeParameters(cfg);
                    break;
                case RUN_PREDICT:
                    new SVM().trainAndPredict(cfg);
                    break;
                case RUN_OPT_AND_PREDICT:
                    break;
            }
            session.getTransaction().commit();
        } catch (HibernateException he) {
            session.getTransaction().rollback();
            logger.error("Database error.", he);
            session.close();
        }
    }
}