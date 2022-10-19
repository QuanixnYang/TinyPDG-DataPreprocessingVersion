public class A{
                public HttpRequest createHttpRequest() {
                    return new HttpRequest() {

                        private byte[] bytes;

                        private Vector<ReadyStateChangeListener> readyStateChangeListeners = new Vector<ReadyStateChangeListener>();

                        public void abort() {
                        }

                        public void addReadyStateChangeListener(ReadyStateChangeListener readyStateChangeListener) {
                            readyStateChangeListeners.add(readyStateChangeListener);
                        }

                        public String getAllResponseHeaders() {
                            return null;
                        }

                        public int getReadyState() {
                            return bytes != null ? STATE_COMPLETE : STATE_UNINITIALIZED;
                        }

                        public byte[] getResponseBytes() {
                            return bytes;
                        }

                        public String getResponseHeader(String arg0) {
                            return null;
                        }

                        public Image getResponseImage() {
                            return bytes != null ? Toolkit.getDefaultToolkit().createImage(bytes) : null;
                        }

                        public String getResponseText() {
                            return new String(bytes);
                        }

                        public Document getResponseXML() {
                            return null;
                        }

                        public int getStatus() {
                            return 200;
                        }

                        public String getStatusText() {
                            return "OK";
                        }

                        public void open(String method, String url) {
                            open(method, url, false);
                        }

                        public void open(String method, URL url) {
                            open(method, url, false);
                        }

                        public void open(String mehod, URL url, boolean async) {
                            try {
                                URLConnection connection = url.openConnection();
                                bytes = new byte[connection.getContentLength()];
                                InputStream inputStream = connection.getInputStream();
                                inputStream.read(bytes);
                                inputStream.close();
                                for (ReadyStateChangeListener readyStateChangeListener : readyStateChangeListeners) {
                                    readyStateChangeListener.readyStateChanged();
                                }
                            } catch (IOException e) {
                            }
                        }

                        public void open(String method, String url, boolean async) {
                            open(method, URLHelper.createURL(url), async);
                        }

                        public void open(String method, String url, boolean async, String arg3) {
                            open(method, URLHelper.createURL(url), async);
                        }

                        public void open(String method, String url, boolean async, String arg3, String arg4) {
                            open(method, URLHelper.createURL(url), async);
                        }
                    };
                }
}