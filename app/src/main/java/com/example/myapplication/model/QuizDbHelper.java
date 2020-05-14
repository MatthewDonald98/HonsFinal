package com.example.myapplication.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

import com.example.myapplication.model.QuizContract.QuestionsTable;

import java.util.ArrayList;
import java.util.List;


public class QuizDbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "MyQuiz4.db";
    private static final int DATABASE_VERSION = 1;

    private static QuizDbHelper instance;

    private SQLiteDatabase db;

    private QuizDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static synchronized QuizDbHelper getInstance(Context context) {
        if (instance == null) {
            instance = new QuizDbHelper(context.getApplicationContext());
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;

        final String SQL_CREATE_CATEGORIES_TABLE = "CREATE TABLE " +
                QuizContract.CategoriesTable.TABLE_NAME + "( " +
                QuizContract.CategoriesTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuizContract.CategoriesTable.COLUMN_NAME + " TEXT " +
                ")";

        final String SQL_CREATE_QUESTIONS_TABLE = "CREATE TABLE " +
                QuestionsTable.TABLE_NAME + " ( " +
                QuestionsTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuestionsTable.COLUMN_QUESTION + " TEXT, " +
                QuestionsTable.COLUMN_OPTION1 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION2 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION3 + " TEXT, " +
                QuestionsTable.COLUMN_ANSWER_NR + " INTEGER, " +
                QuestionsTable.COLUMN_DIFFICULTY + " TEXT, " +
                QuestionsTable.COLUMN_CATEGORY_ID + " INTEGER, " +
                "FOREIGN KEY(" + QuestionsTable.COLUMN_CATEGORY_ID + ") REFERENCES " +
                QuizContract.CategoriesTable.TABLE_NAME + "(" + QuizContract.CategoriesTable._ID + ")" + "ON DELETE CASCADE" +
                ")";

        db.execSQL(SQL_CREATE_CATEGORIES_TABLE);
        db.execSQL(SQL_CREATE_QUESTIONS_TABLE);
        fillCategoriesTable();
        fillQuestionsTable();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + QuizContract.CategoriesTable.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + QuestionsTable.TABLE_NAME);
        onCreate(db);
    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            db.setForeignKeyConstraintsEnabled(true);
        }
    }

    private void fillCategoriesTable() {
        Category c1 = new Category("Phishing");
        insertCategory(c1);
        Category c2 = new Category("Malware");
        insertCategory(c2);
        Category c3 = new Category("Hacker");
        insertCategory(c3);
    }

    public void addCategory(Category category) {
        db = getWritableDatabase();
        insertCategory(category);
    }

    public void addCategories(List<Category> categories) {
        db = getWritableDatabase();

        for (Category category : categories) {
            insertCategory(category);
        }
    }

    private void insertCategory(Category category) {
        ContentValues cv = new ContentValues();
        cv.put(QuizContract.CategoriesTable.COLUMN_NAME, category.getName());
        db.insert(QuizContract.CategoriesTable.TABLE_NAME, null, cv);
    }

    /////WORK HERE///////////
    private void fillQuestionsTable() {
        Question q1 = new Question("Which method is not enough alone to protect from malware?",
                "Using and updating legit security software",
                "Never using an electronic device",
                "Having a firewall on",
                3,
                Question.DIFFICULTY_EASY,
                Category.MALWARE);
        insertQuestion(q1);
        Question q2 = new Question("What is not a type of malware?",
                "Cross site scripting",
                "Worms",
                "Spyware",
                1,
                Question.DIFFICULTY_EASY,
                Category.MALWARE);
        insertQuestion(q2);
        Question q3 = new Question("What does a keylogger virus do?",
                "Stops keys working on the victim's computer",
                "Takes control of the victim's keys",
                "Detects and logs every keystroke made by the user",
                3,
                Question.DIFFICULTY_MEDIUM,
                Category.MALWARE);
        insertQuestion(q3);
        Question q4 = new Question("Malware can be installed without a user's knowledge?",
                "True",
                "False",
                "Don't know",
                1,
                Question.DIFFICULTY_EASY,
                Category.MALWARE);
        insertQuestion(q4);
        Question q5 = new Question("What is a trojan horse virus?",
                "A programme that records every keystroke made by a computer user",
                "A piece of software that looks like it is doing what the user thought, but in reality, is malware",
                "A software that replicate's itself to spread to other computers",
                2,
                Question.DIFFICULTY_HARD, Category.MALWARE);
        insertQuestion(q5);
        Question q6 = new Question("What is malware short for?",
                "Malicious software",
                "Maltreated software",
                "Malleable software",
                1,
                Question.DIFFICULTY_EASY, Category.MALWARE);
        insertQuestion(q6);
        Question q7 = new Question("What is most likely to make your computer stop working?",
                "Trojan",
                "Worm",
                "Virus",
                3,
                Question.DIFFICULTY_HARD,
                Category.MALWARE);
        insertQuestion(q7);
        Question q8 = new Question("What is most likely to send spam emails to your computer?",
                "Spyware",
                "Adware",
                "Trojan",
                2,
                Question.DIFFICULTY_EASY,
                Category.MALWARE);
        insertQuestion(q8);
        Question q9 = new Question("Which malicious programme cannot do anything until the file attached by the malware is activated?",
                "Worm",
                "Spyware",
                "Virus",
                3,
                Question.DIFFICULTY_HARD,
                Category.MALWARE);
        insertQuestion(q9);
        Question q10= new Question("What are the uses of malware?",
                "Used to steal sensitive personal information, financial and business information for the benefit of other outside the organisation",
                "Used against government or corporate websites to gather guarded information or disrupt operations",
                "All of the above",
                3,
                Question.DIFFICULTY_MEDIUM,
                Category.MALWARE);
        insertQuestion(q10);
        Question q11 = new Question("Which of the following is a type of malware?",
                "Keylogger",
                "Packet sniffer",
                "Vulnerability scanner",
                1,
                Question.DIFFICULTY_HARD,
                Category.MALWARE);
        insertQuestion(q11);
        Question q12 = new Question("How can malware spread?",
                "Pirated software",
                "Removable media",
                "All of the above",
                3,
                Question.DIFFICULTY_MEDIUM,
                Category.MALWARE);
        insertQuestion(q12);
        Question q13 = new Question("What type of malware copies itself and damages the system or destroys data?",
                "Virus",
                "Hacker",
                "Rootkit",
                1,
                Question.DIFFICULTY_MEDIUM,
                Category.MALWARE);
        insertQuestion(q13);
        Question q14 = new Question("Rootkits are:",
                "Kits used to treat online malware",
                "software tools that enable an unauthorized user to gain control of a computer system without being detected",
                "a software tool that allows for the owner to spy on activities of the victim system",
                2,
                Question.DIFFICULTY_HARD,
                Category.MALWARE);
        insertQuestion(q14);
        Question q15 = new Question("What is a software that enables a user to obtain secret information about another computer's activities?",
                "Adware",
                "Spyware",
                "Trackware",
                2,
                Question.DIFFICULTY_MEDIUM,
                Category.MALWARE);
        insertQuestion(q15);

        //////////////////////////////MALWARE QS ABOVE//////////////////////////

        Question q16 = new Question("What is phishing?",
                "The use of fraudulent emails to make the receiver hand over personal details such as username/password",
                "A type of attack that allows attackers to inject client side scripts into web pages viewed by other users",
                "A type of attack that is used to infiltrate data driven applications in which malicious sql statement are executed",
                1,
                Question.DIFFICULTY_EASY,
                Category.PHISHING);
        insertQuestion(q16);
        Question q17 = new Question("What type of attack is phishing?",
                "Social engineering",
                "Malware",
                "SQL injection",
                1,
                Question.DIFFICULTY_EASY,
                Category.PHISHING);
        insertQuestion(q17);
        Question q18 = new Question("Which is the safer web address start?",
                "http",
                "https",
                "No difference",
                2,
                Question.DIFFICULTY_MEDIUM,
                Category.PHISHING);
        insertQuestion(q18);
        Question q19 = new Question("Which domain name would you expect if you were to receive an email form PayPal?",
                "@hotmail.com",
                "@gmail.com",
                "@paypal.com",
                3,
                Question.DIFFICULTY_EASY,
                Category.PHISHING);
        insertQuestion(q19);
        Question q20 = new Question("What is the name for a phishing attack that targets specific users?",
                "Whale phishing",
                "Spear phishing",
                "Cat phishing",
                2,
                Question.DIFFICULTY_HARD,
                Category.PHISHING);
        insertQuestion(q20);
        Question q21 = new Question("What kind of information would you expect an attacker to be looking for?",
                "Favourite food",
                "Where you work",
                "Login credentials",
                3,
                Question.DIFFICULTY_EASY,
                Category.PHISHING);
        insertQuestion(q21);
        Question q22 = new Question("What site generates the most clicks in response to phishing emails?",
                "Facebook",
                "Twitter",
                "LinkedIn",
                3,
                Question.DIFFICULTY_HARD,
                Category.PHISHING);
        insertQuestion(q22);
        Question q23 = new Question("What content would you expect to find inside a phishing email?",
                "A payment failing to complete",
                "A reminder for a dental appointment",
                "A newsletter",
                1,
                Question.DIFFICULTY_EASY,
                Category.PHISHING);
        insertQuestion(q23);
        Question q24 = new Question("What is the most common phishing email subject?",
                "Password check required",
                "A delivery attempt was made",
                "Change of password required immediately",
                3,
                Question.DIFFICULTY_HARD,
                Category.PHISHING);
        insertQuestion(q24);
        Question q25 = new Question("You receive a text telling you, you need to update a password for software you use, what do you do?",
                "Reply to the text to confirm it is legit",
                "Call the vendor on a number you know and make sure the request is legit",
                "Click the link and investigate the website",
                2,
                Question.DIFFICULTY_MEDIUM,
                Category.PHISHING);
        insertQuestion(q25);
        Question q26 = new Question("What is the recommended password change interval?",
                "7 days",
                "30 days",
                "60 days",
                2,
                Question.DIFFICULTY_HARD,
                Category.PHISHING);
        insertQuestion(q26);
        Question q27 = new Question("If you fall for a phishing scam, what can be done to limit the damage?",
                "Delete the email",
                "Change compromised passwords",
                "Unplug the computer",
                2,
                Question.DIFFICULTY_MEDIUM,
                Category.PHISHING);
        insertQuestion(q27);
        Question q28 = new Question("Occasionally phishing sites can be spotted by a lack of security. A secure socket layer is is supposed to be much safer. Which of these is a way of initiating such a connection?",
                "Switching to a new ethernet cable",
                "Opening the site in another tab",
                "Typing https:// in-front of the site address",
                3,
                Question.DIFFICULTY_HARD,
                Category.PHISHING);
        insertQuestion(q28);
        Question q29 = new Question("Which of these methods is the most cost effective way to prevent social engineering attacks?",
                "Ensure that patches are up-to-date",
                "Implement user awareness training",
                "Monitor and control all email activity",
                2,
                Question.DIFFICULTY_MEDIUM,
                Category.PHISHING);
        insertQuestion(q29);
        Question q30 = new Question("Which of the following scenarios can be executed via phishing?",
                "Facebook messaging link scams",
                "Banking link scams",
                "All of the above",
                3,
                Question.DIFFICULTY_MEDIUM,
                Category.PHISHING);
        insertQuestion(q30);
        //////////////////// PHISHING Q'S ABOVE/////////////////////////

        Question q31= new Question("What is hacking?",
                "Stealing user passwords",
                "Gaining access to other computers without their permission",
                "Using cheats in games",
                2,
                Question.DIFFICULTY_EASY,
                Category.HACKER);
        insertQuestion(q31);
        Question q32= new Question("When ia hacking legal?",
                "Never legal",
                "When hired by a company to test security",
                "Always legal",
                2,
                Question.DIFFICULTY_EASY,
                Category.HACKER);
        insertQuestion(q32);
        Question q33= new Question("What is SQL injection?",
                "A programming language",
                "A type of exploit in which the attacker adds SQL code to an input box in order to make changes to data or access resources",
                "A script language that is primarily used in client side JavaScript in order to enhance user experience",
                2,
                Question.DIFFICULTY_HARD,
                Category.HACKER);
        insertQuestion(q33);
        Question q34= new Question("How can SQL injection be prevented?",
                "By sanitising user input",
                "Bu making your code public",
                "All of the above",
                1,
                Question.DIFFICULTY_HARD,
                Category.HACKER);
        insertQuestion(q34);
        Question q35= new Question("What is white hat hacking?",
                "A hacker wearing a white hat",
                "Someone who breaks into the system with malicious intent",
                "Someone who specialises in penetrative and other testing methodologies to ensure the security of an organisations system",
                3,
                Question.DIFFICULTY_MEDIUM,
                Category.HACKER);
        insertQuestion(q35);
        Question q36= new Question("What is cross site scripting?",
                "A programming language that allows control of multiple applications",
                "A type of security vulnerability typically found in web applications that allows attackers to inject client side script that can be viewed by other users",
                "A scripting language used for controlling a computer",
                2,
                Question.DIFFICULTY_HARD,
                Category.HACKER);
        insertQuestion(q36);
        Question q37= new Question("What is social engineering?",
                "The manipulation of DNA",
                "An engineering discipline",
                "The art of manipulating people into performing actions or divulging information",
                3,
                Question.DIFFICULTY_EASY,
                Category.HACKER);
        insertQuestion(q37);
        Question q38= new Question("Hackers and ethical hackers use the same techniques?",
                "True",
                "False",
                "Don't know",
                1,
                Question.DIFFICULTY_EASY,
                Category.HACKER);
        insertQuestion(q38);
        Question q39= new Question("What are the three types of hackers?",
                "White hat, black hat, grey hat",
                "Red hat, yellow hat, green hat",
                "Red hat, yellow hat, blue hat",
                1,
                Question.DIFFICULTY_EASY,
                Category.HACKER);
        insertQuestion(q39);
        Question q40= new Question("What is the first phase of hacking?",
                "Gaining access",
                "Scanning",
                "Reconnaissance",
                3,
                Question.DIFFICULTY_MEDIUM,
                Category.HACKER);
        insertQuestion(q40);
        Question q41= new Question("When a hacker attempts to attack over the internet this is called?",
                "Internet attack",
                "Remote attack",
                "Online attack",
                2,
                Question.DIFFICULTY_MEDIUM,
                Category.HACKER);
        insertQuestion(q41);
        Question q42= new Question("What is the process of encoding information in a way that only someone with a key can see it?",
                "Encryption",
                "Compression",
                "Decoding",
                1,
                Question.DIFFICULTY_MEDIUM,
                Category.HACKER);
        insertQuestion(q42);
        Question q43= new Question("What does SSL stand for?",
                "Systematic security level",
                "Secure server logarithm",
                "Secure socket layer",
                3,
                Question.DIFFICULTY_HARD,
                Category.HACKER);
        insertQuestion(q43);
        Question q44= new Question("What is a firewall?",
                "Antivirus software",
                "Software that stops an internet connection",
                "A filter for an internet connection",
                3,
                Question.DIFFICULTY_MEDIUM,
                Category.HACKER);
        insertQuestion(q44);
        Question q45= new Question("Which od the following is not a factor while securing the network against an attack?",
                "The network architecture",
                "The business strategy of the company",
                "The level of access provided to employees",
                2,
                Question.DIFFICULTY_HARD,
                Category.HACKER);
        insertQuestion(q45);




    }

    public void addQuestion(Question question) {
        db = getWritableDatabase();
        insertQuestion(question);
    }

    public void addQuestions(List<Question> questions) {
        db = getWritableDatabase();

        for (Question question : questions) {
            insertQuestion(question);
        }
    }

    private void insertQuestion(Question question) {
        ContentValues cv = new ContentValues();
        cv.put(QuestionsTable.COLUMN_QUESTION, question.getQuestion());
        cv.put(QuestionsTable.COLUMN_OPTION1, question.getOption1());
        cv.put(QuestionsTable.COLUMN_OPTION2, question.getOption2());
        cv.put(QuestionsTable.COLUMN_OPTION3, question.getOption3());
        cv.put(QuestionsTable.COLUMN_ANSWER_NR, question.getAnswerNr());
        cv.put(QuestionsTable.COLUMN_DIFFICULTY, question.getDifficulty());
        cv.put(QuestionsTable.COLUMN_CATEGORY_ID, question.getCategoryID());
        db.insert(QuestionsTable.TABLE_NAME, null, cv);
    }

    public List<Category> getAllCategories() {
        List<Category> categoryList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + QuizContract.CategoriesTable.TABLE_NAME, null);

        if (c.moveToFirst()) {
            do {
                Category category = new Category();
                category.setId(c.getInt(c.getColumnIndex(QuizContract.CategoriesTable._ID)));
                category.setName(c.getString(c.getColumnIndex(QuizContract.CategoriesTable.COLUMN_NAME)));
                categoryList.add(category);
            } while (c.moveToNext());
        }

        c.close();
        return categoryList;
    }

    public ArrayList<Question> getAllQuestions() {
        ArrayList<Question> questionList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + QuestionsTable.TABLE_NAME, null);

        if (c.moveToFirst()) {
            do {
                Question question = new Question();
                question.setId(c.getInt(c.getColumnIndex(QuestionsTable._ID)));
                question.setQuestion(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION3)));
                question.setAnswerNr(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_ANSWER_NR)));
                question.setDifficulty(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_DIFFICULTY)));
                question.setCategoryID(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_CATEGORY_ID)));
                questionList.add(question);
            } while (c.moveToNext());
        }

        c.close();
        return questionList;
    }

    public ArrayList<Question> getQuestions(int categoryID, String difficulty) {
        ArrayList<Question> questionList = new ArrayList<>();
        db = getReadableDatabase();

        String selection = QuestionsTable.COLUMN_CATEGORY_ID + " = ? " +
                " AND " + QuestionsTable.COLUMN_DIFFICULTY + " = ? ";
        String[] selectionArgs = new String[]{String.valueOf(categoryID), difficulty};

        Cursor c = db.query(
                QuestionsTable.TABLE_NAME,
                null,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        if (c.moveToFirst()) {
            do {
                Question question = new Question();
                question.setId(c.getInt(c.getColumnIndex(QuestionsTable._ID)));
                question.setQuestion(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION3)));
                question.setAnswerNr(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_ANSWER_NR)));
                question.setDifficulty(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_DIFFICULTY)));
                question.setCategoryID(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_CATEGORY_ID)));
                questionList.add(question);
            } while (c.moveToNext());
        }

        c.close();
        return questionList;
    }
}
