public class A{
    public Vector<Question> reload() throws IOException {
        Vector<Question> questions = new Vector<Question>();
        InputStream is = url.openStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        shortName = br.readLine();
        if (shortName != null && shortName.equals("SHORTNAME")) {
            shortName = br.readLine();
            author = br.readLine();
            if (author != null && author.equals("AUTHOR")) {
                author = br.readLine();
                description = br.readLine();
                if (description != null && description.equals("DESCRIPTION")) {
                    description = br.readLine();
                    try {
                        questions = QuestionLoader.getQuestions(br);
                    } catch (IOException ioe) {
                        ioe.printStackTrace();
                        throw ioe;
                    } finally {
                        br.close();
                        is.close();
                    }
                } else {
                    throw new IllegalArgumentException();
                }
            } else {
                throw new IllegalArgumentException();
            }
        } else {
            throw new IllegalArgumentException();
        }
        return questions;
    }
}