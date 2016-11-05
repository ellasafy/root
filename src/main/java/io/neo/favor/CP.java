package io.neo.favor;

/**
 * Created by lunjianchang on 7/25/16.
 */
public class CP {
    int t = 1;

    public static void main(String[] args) {
        CP main = new CP();
        Thread c = new C(main);
        Thread p = new P(main);
        c.start();
        p.start();
    }

    static class C extends Thread{
        private CP main;
        public C(CP main) {
            this.main = main;
        }
        public void run() {
            while(true) {
                synchronized (main) {
                    if (main.t >= 100) {
                        break;
                    }
                    if (main.t %2 ==1 && main.t <100) {
                        System.out.println("c : " +main.t);

                        main.t = main.t +1;
                        main.notify();
                    }else {
                        try {
                            main.wait();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                }
            }

        }
    }

    static class P extends  Thread {
        private CP main;
        public P(CP main) {
            this.main = main;
        }

        public void run() {
            while (true) {
                synchronized(main) {
                    if (main.t >=100) {
                        break;
                    }
                    if (main.t %2 ==0 && main.t <100) {
                        System.out.println("p : " +main.t);

                        main.t = main.t +1;
                        main.notify();
                    }else {
                        try {
                            main.wait();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                }
            }

        }

    }
}
