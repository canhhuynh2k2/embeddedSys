package vn.edu.ptit.quiz.config;

public interface AppConstant {
    public interface ADMIN {
        public static final String ADMIN_USERNAME = "admin";
        public static final String ADMIN_PASSWORD = "admin";
        public static final String ADMIN_TOKEN = "f9960f56a72ec9c29be0fcbea5e00af9";
    }

    public interface QUIZ {
        interface STATUS {
            public static final Integer ACTIVE = 1;
            public static final Integer NOT_ACTIVE = 0;
        }
    }

    public interface STUDENT {
        interface STATUS {
            public static final Integer ACTIVE = 1;
            public static final Integer NOT_ACTIVE = 0;
        }
    }

    public interface ANSWER {
        public static final String SECRET_KEY = "31b2e997d4cf2e3d6c0cca5244c97115531b8e838179ffb7ec0263404c7d074e";
    }
}
