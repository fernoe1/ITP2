import javax.swing.*;
import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class FullPoliticalQuiz {
    static class QuizQuestion {
        String question;
        java.util.List<String> options;
        String correctAnswer;

        public QuizQuestion(String question, java.util.List<String> options, String correctAnswer) {
            this.question = question;
            this.options = new ArrayList<>(options);
            this.correctAnswer = correctAnswer;
        }
    }

    private java.util.List<QuizQuestion> questions;
    private int currentQuestionIndex = 0;
    private int score = 0;
    private int totalQuestions;
    private JFrame frame;
    private JLabel questionLabel;
    private JRadioButton[] optionButtons;
    private ButtonGroup optionsGroup;
    private JButton nextButton, prevButton;

    private String[] userAnswers;

    public FullPoliticalQuiz() {
        initQuestions();
        totalQuestions = questions.size();
        userAnswers = new String[totalQuestions];

        Collections.shuffle(questions);

        buildUI();
        loadQuestion();
    }

    private void initQuestions() {
        questions = new ArrayList<>();

        // QUIZ 1
        questions.add(new QuizQuestion("What is Politics according to Aristotle?",
                Arrays.asList("Politics as the study of power", "Politics as the master science", "Politics as the authoritative allocation of values", "Who gets what, when and how"),
                "Politics as the master science"));
        questions.add(new QuizQuestion("Who is the author of 'The Republic'?",
                Arrays.asList("Socrates", "Plato", "Aristotle", "Niccolo Machiavelli"),
                "Plato"));
        questions.add(new QuizQuestion("In which historical period did religious institutions hold tremendous political power?",
                Arrays.asList("Ancient Age", "Middle Ages", "Early Modern Period", "Modern Period"),
                "Middle Ages"));
        questions.add(new QuizQuestion("Which subfield of political science studies a nation’s policies and policy development?",
                Arrays.asList("Domestic policy", "Comparative policy", "International policy", "All mentioned"),
                "Domestic policy"));
        questions.add(new QuizQuestion("Which factors influence politics according to Montesquieu?",
                Arrays.asList("History and mentality", "Climate and geography", "Economics and culture", "National language and traditions"),
                "Climate and geography"));
        questions.add(new QuizQuestion("What are the main methodologies of Political Science?",
                Arrays.asList("Qualitative methods", "Case studies, Survey research, Experiments", "Quantitative methods", "All the mentioned"),
                "All the mentioned"));
        questions.add(new QuizQuestion("Who considered politics as the authoritative allocation of values?",
                Arrays.asList("David Easton", "Harold Lawell", "Andrew Heywood", "Henry Sidgwick"),
                "David Easton"));
        questions.add(new QuizQuestion("What is politics according to Andrew Heywood?",
                Arrays.asList("Politics as an activity and a process", "Politics as the master science", "Who gets what, when and how", "Politics as the art of government"),
                "Politics as the art of government"));
        questions.add(new QuizQuestion("Right form of government according to Aristotle:",
                Arrays.asList("Democracy", "Tyranny", "Oligarchy", "Polity"),
                "Polity"));
        questions.add(new QuizQuestion("Political science is mostly called an interdisciplinary subject that includes other disciplines, such as:",
                Arrays.asList("Philosophy, Sociology", "Psychology, History, Anthropology", "Economy, Economy", "All the mentioned"),
                "All the mentioned"));

        // QUIZ 2
        questions.add(new QuizQuestion("What is the term for the ability to influence someone to act in a way that is contrary to their own will?",
                Arrays.asList("Power", "Legitimacy", "Law", "Charisma"),
                "Power"));
        questions.add(new QuizQuestion("Give the definition of the coercive type of power?",
                Arrays.asList("The power to force compliance through the threat of punishment or other negative consequences",
                        "A type of power that involves the ability to offer incentives or rewards to influence behavior",
                        "A type of power that derives from an individual’s formal authority",
                        "A type of power that derives from personal qualities"),
                "The power to force compliance through the threat of punishment or other negative consequences"));
        questions.add(new QuizQuestion("What type of legitimate rule is represented by the Japanese Emperor or The Vatican?",
                Arrays.asList("Traditional", "Rational", "Legal", "Charismatic"),
                "Traditional"));
        questions.add(new QuizQuestion("The belief that those who hold power have the right to do so is called...",
                Arrays.asList("Power", "Legitimacy", "Law", "Coercion"),
                "Legitimacy"));
        questions.add(new QuizQuestion("Who distinguished 3 ideal types of legitimate ruler?",
                Arrays.asList("Max Weber", "Karl Marx", "Elen Origely", "French and Raven"),
                "Max Weber"));
        questions.add(new QuizQuestion("Find the example of referent type of power",
                Arrays.asList("The use of physical force or the threat of arrest by police officers",
                        "Students scholarships that motivate to study well",
                        "The authority of parents to make decisions on behalf of their children (legally)",
                        "The power of celebrities who can influence public opinion through their endorsements"),
                "The power of celebrities who can influence public opinion through their endorsements"));
        questions.add(new QuizQuestion("Type of power that is explicitly tied to the functioning of political institutions and processes",
                Arrays.asList("Economic power", "Social Power", "Political Power", "Military Power"),
                "Political Power"));
        questions.add(new QuizQuestion("To which spheres can 'videomalaise' be referred in the modern period (according to K. Newton, 2008)?",
                Arrays.asList("Television and newspapers", "Television news on political attitudes and opinion", "Television, newspapers, radio, the World Wide Web", "Newspapers on political and economic opinion"),
                "Television, newspapers, radio, the World Wide Web"));
        questions.add(new QuizQuestion("What is public opinion based on according to K. Newton, 2008?",
                Arrays.asList("Television, radio, newspapers", "Opinions, attitudes, values", "Vision, education, experience", "Social status, ideology, gender"),
                "Opinions, attitudes, values"));
        questions.add(new QuizQuestion("What paradox(es) constrains the impacts of the media (PK. Newton, 2008)?",
                Arrays.asList("The more people know about and have firsthand experienced something, the more likely they are to trust media’s judgments",
                        "Those who are least interested, involved and knowledgeable about politics are the most distrustful to media influence",
                        "The more partisan the mass media and the more they try to persuade people of a political position, the less likely they are to be seen as impartial and neutral, and the less likely they are to be influential",
                        "All the mentioned"),
                "The more partisan the mass media and the more they try to persuade people of a political position, the less likely they are to be seen as impartial and neutral, and the less likely they are to be influential"));

        // QUIZ 3
        questions.add(new QuizQuestion("What is the core of the elitist theory?",
                Arrays.asList("that there is a coherent or unified power elite that dominates society",
                        "that the politics functions through interest groups",
                        "everyone can be an elite",
                        "elites are the smartest people"),
                "that there is a coherent or unified power elite that dominates society"));
        questions.add(new QuizQuestion("According to Mills, the members of the power elite:",
                Arrays.asList("is simply the superior part of the political organization",
                        "occupy the strategic command posts of the social structure, in which are now centered the effective means of power, wealth, and celebrity",
                        "persons who are directly or indirectly concerned with administration",
                        "performs all of the political functions, monopolizes power, and enjoys the advantages that power brings"),
                "occupy the strategic command posts of the social structure, in which are now centered the effective means of power, wealth, and celebrity"));
        questions.add(new QuizQuestion("Who is the author of 'The Ruling Class' (1996)?",
                Arrays.asList("V. Pareto", "G. Mosca", "R. Michels", "C. Mills"),
                "G. Mosca"));
        questions.add(new QuizQuestion("To whom belongs the theory of the 'circulation of elites'?",
                Arrays.asList("G. Mosca", "R. Michels", "V. Pareto", "C. Mills"),
                "V. Pareto"));
        questions.add(new QuizQuestion("What is a 'Polyarchy'?",
                Arrays.asList("society with a tiny elite at the top",
                        "the collection of billiard balls colliding with each other and with government to produce policy",
                        "the rule of the leaders of several groups who have reached stable understandings with each other",
                        "all the mentioned"),
                "the rule of the leaders of several groups who have reached stable understandings with each other"));
        questions.add(new QuizQuestion("Choose the right explanation of trait theory:",
                Arrays.asList("concentrates on the qualities and traits of leaders",
                        "emphasizes that the leader provides benefits to his followers",
                        "believes that leadership emerges from the situation and is influenced by the situation",
                        "focuses on the actions of leaders and holds that other leaders are able to imitate similar actions"),
                "concentrates on the qualities and traits of leaders"));
        questions.add(new QuizQuestion("What is democratic leadership?",
                Arrays.asList("when the leaders give directives with little input",
                        "is a collective leadership system that allows for various contributions",
                        "when the leader challenges his followers to achieve objectives",
                        "is an approach that emphasizes responding to situations as they occur"),
                "is a collective leadership system that allows for various contributions"));
        questions.add(new QuizQuestion("When was the cult of personality popularized?",
                Arrays.asList("when Napoleon III seized power in France",
                        "when Stalin decided to embalm Lenin’s corpse",
                        "when Augustus started deifying emperors",
                        "when Nikita Khrushchev denounced the cult of personality surrounding Stalin"),
                "when Nikita Khrushchev denounced the cult of personality surrounding Stalin"));
        questions.add(new QuizQuestion("Which tool did Stalin use in order to increase his popularity among society?",
                Arrays.asList("he released his own \"Short Biography\"",
                        "he printed a coin with his name",
                        "he erected a monument of himself",
                        "he gave money to those in need"),
                "he released his own \"Short Biography\""));
        questions.add(new QuizQuestion("Which statement best describes the view of the author?",
                Arrays.asList("Stalin's methods of increasing his popularity were ineffective",
                        "Stalin aimed to increase credibility and authority",
                        "Stalin clearly viewed charismatic leadership as a way to bolster the Soviet system",
                        "Stalin didn't have any features of the 'Cult of Personality'"),
                "Stalin clearly viewed charismatic leadership as a way to bolster the Soviet system"));

        // QUIZ 4
        questions.add(new QuizQuestion("The word 'ideologie' first made its appearance in French, when introduced by A.-L.C. Destutt de Tracy. What historical event led to that?",
                Arrays.asList("French Revolution", "Industrial Revolution", "World War I", "World War II"),
                "French Revolution"));
        questions.add(new QuizQuestion("A political ideology is",
                Arrays.asList("An answer to the question \"who gets what, when, and how.\"",
                        "A set of principles, laws, ideas, and procedures relating to a particular form of government.",
                        "A coherent set of views on politics and the role of the government.",
                        "All mentioned"),
                "A coherent set of views on politics and the role of the government."));
        questions.add(new QuizQuestion("Which belief best describes nationalism?",
                Arrays.asList("The belief that inequalities in the economy destroy any equality in politics",
                        "The belief that one’s nation is great",
                        "The belief in the innate goodness of the individual",
                        "The belief that one person is chosen by God to rule over the rest"),
                "The belief that one’s nation is great"));
        questions.add(new QuizQuestion("What type of political ideology focuses on tradition and inherited practices?",
                Arrays.asList("Conservatism", "Socialism", "Liberalism", "Nationalism"),
                "Conservatism"));
        questions.add(new QuizQuestion("The core ideas of liberalism are:",
                Arrays.asList("tradition, human imperfection, society, hierarchy, authority, and property",
                        "nations, self-determination, nation-states, racism, and internationalism",
                        "ecology, environmental ethics, environmental consciousness",
                        "freedom, individualism, rationalism, the liberal state, and social justice"),
                "freedom, individualism, rationalism, the liberal state, and social justice"));
        questions.add(new QuizQuestion("Which of the following is not a factor of political socialization?",
                Arrays.asList("Family", "Education", "Media", "All mentioned"),
                "All mentioned"));
        questions.add(new QuizQuestion("Which of the following types of political culture identified by Almond and Verba are interested in politics, but citizens have low cognitive, effective, and evaluative orientation in the political system?",
                Arrays.asList("Subject", "Parochial", "Participant", "All mentioned"),
                "Parochial"));
        questions.add(new QuizQuestion("Which things determine political culture?",
                Arrays.asList("People's orientation", "The attitudes of the people", "Their role", "All mentioned"),
                "All mentioned"));
        questions.add(new QuizQuestion("Which ideology is most compatible with individualistic political culture?",
                Arrays.asList("Conservatism", "Liberalism", "Feminism", "Socialism"),
                "Liberalism"));
        questions.add(new QuizQuestion("Bobbio argued that the fundamental basis for the distinction between left and right political ideologies lies in:",
                Arrays.asList("differing attitudes to equality", "attitudes to political change", "attitudes to economic organization", "the role of the state"),
                "differing attitudes to equality"));

        // QUIZ 5
        questions.add(new QuizQuestion("What was the relationship found between left-wing political orientation and social distancing attitudes (N. Terry et al, 2022)?",
                Arrays.asList("No significant relationship was found",
                        "Left-wing political orientation was associated with more positive and less negative social distancing attitudes",
                        "Left-wing political orientation was associated with more negative and less positive social distancing attitudes",
                        "There was a stronger association between right-wing political orientation and positive social distancing attitudes"),
                "Left-wing political orientation was associated with more positive and less negative social distancing attitudes"));
        questions.add(new QuizQuestion("What were the significant correlations found between political orientation and endorsement of moral foundations (H. Terry et al, 2022)?",
                Arrays.asList("There were no significant correlations found",
                        "Left-wing political orientation was positively associated with endorsement of binding foundations and economic liberty items",
                        "Left-wing political orientation was positively associated with endorsement of individualizing moral foundations and negatively associated with binding foundations and economic liberty items",
                        "Left-wing political orientation was associated with more negative social distancing attitudes"),
                "Left-wing political orientation was positively associated with endorsement of individualizing moral foundations and negatively associated with binding foundations and economic liberty items"));
        questions.add(new QuizQuestion("What political theory advocates for maximizing the greatest happiness of the greatest number?",
                Arrays.asList("Utilitarianism", "Conservatism", "Marxism", "Feminism"),
                "Utilitarianism"));
        questions.add(new QuizQuestion("Who is considered as a founder of classical utilitarianism?",
                Arrays.asList("Karl Marx", "Jeremy Bentham", "Jean-Jacques Rousseau", "Edmund Burke"),
                "Jeremy Bentham"));
        questions.add(new QuizQuestion("What concept is at the core of the Marxist tradition?",
                Arrays.asList("Content with the government", "ANirm the traditions that you've inherited", "Exploitation of working class", "The principle of aNected interest"),
                "Exploitation of working class"));
        questions.add(new QuizQuestion("What is the Social Contract political theory?",
                Arrays.asList("Uniting or eliminating exploitation", "The idea of agreement or consent as the basis for government", "Maximizing the greatest happiness of the greatest number", "ANirm the traditions that you've inherited"),
                "The idea of agreement or consent as the basis for government"));
        questions.add(new QuizQuestion("What political theory best describes a society where people give up individual freedom in exchange for peace and protection?",
                Arrays.asList("Utilitarianism", "Marxism", "Democracy", "Social Contract"),
                "Social Contract"));
        questions.add(new QuizQuestion("Select the political theorists of Social Contract?",
                Arrays.asList("J. Bentham and J. S. Mill", "K. Marx and F. Engels", "T. Hobbes and J. J. Rousseau", "E. Burke and P. M. Foucault"),
                "T. Hobbes and J. J. Rousseau"));
        questions.add(new QuizQuestion("In which experiment is the problem of obedience to authority considered (\"How Far Will You Go to Obey an Order?\")?",
                Arrays.asList("Milgram's obedience experiment", "Stanford Prison Experiment", "Univers - 25: The Mouse ‘Utopia’ Experiment", "All the mentioned"),
                "Milgram's obedience experiment"));
        questions.add(new QuizQuestion("How does left/right of the political spectrum affect moral foundations of people (H. Terry et al, 2022)?",
                Arrays.asList("Those on the left more heavily endorse care and fairness",
                        "Those on the right tend to endorse individualizing foundations",
                        "Those on the left tend to support binding foundations",
                        "Those on the left more strongly endorsing the economic liberty items, and a lack of difference on lifestyle liberty"),
                "Those on the left more heavily endorse care and fairness"));

        // QUIZ 6
        questions.add(new QuizQuestion("Political regime can be divided into:",
                Arrays.asList("democracy, totalitarianism, authoritarianism", "unitary, federal, confederal", "monarchy and democracy", "first, second, third"),
                "democracy, totalitarianism, authoritarianism"));
        questions.add(new QuizQuestion("Choose the relevant definition of civil society:",
                Arrays.asList("a realm where citizens associate according to their own interests and wishes",
                        "a set of rules and practices that inform relationships",
                        "an organized group seeking to influence public policy",
                        "organizations that claim a monopoly on violence"),
                "a realm where citizens associate according to their own interests and wishes"));
        questions.add(new QuizQuestion("What is a political institution?",
                Arrays.asList("Social institutions including the state, parties, and public organizations",
                        "Organizations in government that create and enforce laws",
                        "Organizations that mediate conflict and provide representation",
                        "All of the mentioned"),
                "All of the mentioned"));
        questions.add(new QuizQuestion("What is a state?",
                Arrays.asList("A set of rules that inform relationships",
                        "A political organization with functions including security and revenue",
                        "An organized group aiming to influence public policy",
                        "A realm of autonomous groups"),
                "A political organization with functions including security and revenue"));
        questions.add(new QuizQuestion("Choose the definition of natural theory of state’s origin:",
                Arrays.asList("The state is established by God(s)",
                        "Where one group forced all people to obey",
                        "Where the state evolved from lower associations (household/family)",
                        "State is an artificial institution from a social contract"),
                "Where the state evolved from lower associations (household/family)"));
        questions.add(new QuizQuestion("What are the main characteristics of the state?",
                Arrays.asList("Population, Territory, Sovereignty",
                        "Territory, Sovereignty",
                        "Sovereignty, Legitimacy, Organization",
                        "Sovereignty, Legitimacy, Organization, Population, Territory"),
                "Sovereignty, Legitimacy, Organization, Population, Territory"));
        questions.add(new QuizQuestion("According to history, economic structure, and economic organization states are divided into:",
                Arrays.asList("first, second, third world countries", "monarchy and republic", "unitary and federal", "totalitarian, authoritarian, democratic"),
                "first, second, third world countries"));
        questions.add(new QuizQuestion("What is a monarchy?",
                Arrays.asList("When the head of State is chosen by citizens",
                        "States create different divisions of government",
                        "A form of government where all citizens have equal say",
                        "Sovereignty embodied in a single individual"),
                "Sovereignty embodied in a single individual"));
        questions.add(new QuizQuestion("What are the types of Democracy:",
                Arrays.asList("Direct and representative", "Direct and constitutional", "Direct, representative, mixed", "Direct, constitutional, mixed"),
                "Direct and representative"));
        questions.add(new QuizQuestion("Choose the three branches of governmental power:",
                Arrays.asList("Civil society, political party, unions",
                        "Legislative, executive, judicial",
                        "President, monarch, prime minister",
                        "Direct, indirect, representative"),
                "Legislative, executive, judicial"));

        // QUIZ 7
        questions.add(new QuizQuestion("What is a political party?",
                Arrays.asList("A group identified by name and ideology that fields candidates at elections",
                        "A general term for election rules",
                        "A set of rules for relationships",
                        "A realm where citizens associate by interests"),
                "A group identified by name and ideology that fields candidates at elections"));
        questions.add(new QuizQuestion("According to Edmund Burke, a political party:",
                Arrays.asList("Advances the interests of a particular group",
                        "Is a body of men united for promoting national interests",
                        "Focuses the political feelings of its citizens",
                        "Is necessary for democracy"),
                "Is a body of men united for promoting national interests"));
        questions.add(new QuizQuestion("According to Maurice Duverger, what are the types of parties?",
                Arrays.asList("Direct and indirect parties",
                        "Mono and multi parties",
                        "Short and long-term parties",
                        "Mass and cadre (elite) parties"),
                "Mass and cadre (elite) parties"));
        questions.add(new QuizQuestion("What is the main purpose of catch-all parties?",
                Arrays.asList("To govern in the national interest", "To represent social groups", "To gather people around one ideology", "To fight with other parties"),
                "To govern in the national interest"));
        questions.add(new QuizQuestion("What functions of parties were distinguished by Avron Morse?",
                Arrays.asList("Education and organization of public opinion/administration of government",
                        "Union, recruitment",
                        "Enthusiasm, instruction",
                        "Mobilizing citizens and recruiting leaders"),
                "Education and organization of public opinion/administration of government"));
        questions.add(new QuizQuestion("What is a party system?",
                Arrays.asList("Interactions of parties with each other",
                        "Party system is the same as party",
                        "Hierarchy between parties",
                        "Classification of parties"),
                "Interactions of parties with each other"));
        questions.add(new QuizQuestion("Types of party system:",
                Arrays.asList("Single party, two-party", "No-party, single party, dominant party, two-party, multi-party", "No-party, single party, dominant party", "No-party, single party, two-party, multi-party"),
                "No-party, single party, dominant party, two-party, multi-party"));
        questions.add(new QuizQuestion("What is an Electoral System?",
                Arrays.asList("A general term for the rules governing an election",
                        "A group identified by name and ideology",
                        "An association that presumes government for policies it favors",
                        "An organization with rules for relationships"),
                "A general term for the rules governing an election"));
        questions.add(new QuizQuestion("According to Ian Shapiro on Political Parties, Weak parties:",
                Arrays.asList("Retail, not wholesale campaigning",
                        "Candidates and party platforms controlled from below",
                        "Legislative leaders cannot set the agenda",
                        "All the mentioned"),
                "All the mentioned"));
        questions.add(new QuizQuestion("According to Ian Shapiro on Political Parties, characteristics of strong parties:",
                Arrays.asList("Unified, centralized, stable, organizationally complex",
                        "Leaders have enough rope to hang themselves",
                        "Everyone is one team",
                        "All the mentioned"),
                "All the mentioned"));

        // QUIZ 8
        questions.add(new QuizQuestion("According to Primbistrow et al, 2022, why does energy consumption negatively impact environmental deterioration in Kazakhstan?",
                Arrays.asList("Because more than 90% of Kazakhstan’s energy demand is fulfilled by fossil fuels",
                        "Because economic globalization contributes to environmental degradation",
                        "Because social globalization negatively impacts carbon emissions",
                        "Because energy consumption is not a major contributor"),
                "Because more than 90% of Kazakhstan’s energy demand is fulfilled by fossil fuels"));
        questions.add(new QuizQuestion("What is the relationship between economic and political globalization and carbon emissions in Kazakhstan (Primbistrow et al, 2022)?",
                Arrays.asList("Economic and political globalization have a negative and substantial impact on carbon emissions",
                        "Economic and political globalization have a positive impact on carbon emissions",
                        "Economic globalization has a negative impact while political has a positive impact",
                        "Economic and political globalization do not have any impact"),
                "Economic and political globalization have a negative and substantial impact on carbon emissions"));
        questions.add(new QuizQuestion("Select the argument against globalization:",
                Arrays.asList("The world economy is more interdependent than ever",
                        "Globalization is merely a buzzword to denote the latest phase of capitalism",
                        "People are beginning to “think globally and act locally”",
                        "Electronic communications alter our notions of social groups"),
                "Globalization is merely a buzzword to denote the latest phase of capitalism"));
        questions.add(new QuizQuestion("What does ‘bipolarity’ mean in world politics?",
                Arrays.asList("The presence or rivalry between two superpowers",
                        "The hegemony of one hyperpower state",
                        "The presence or rivalry between three or more superpowers",
                        "The notion of the world as a united polity"),
                "The presence or rivalry between two superpowers"));
        questions.add(new QuizQuestion("The adoption of the practices and culture of western Europe by societies in other parts of the world is called:",
                Arrays.asList("globalization", "westernization", "modernization", "urbanization"),
                "westernization"));
        questions.add(new QuizQuestion("The process by which ideas, knowledge, information, goods and services spread around the world is called:",
                Arrays.asList("globalization", "westernization", "modernization", "urbanization"),
                "globalization"));
        questions.add(new QuizQuestion("Set of related theories of international relations emphasizing the role of the state, national interest, and power is:",
                Arrays.asList("Idealism", "Bipolarity", "Realism", "Unipolarity"),
                "Realism"));
        questions.add(new QuizQuestion("The transformation from a traditional, rural, agrarian society to a secular, urban, industrial society is called:",
                Arrays.asList("globalization", "westernization", "modernization", "urbanization"),
                "modernization"));
        questions.add(new QuizQuestion("A system of world order in which global influences are held between two states is called:",
                Arrays.asList("Idealism", "Bipolarity", "Realism", "Unipolarity"),
                "Bipolarity"));
        questions.add(new QuizQuestion("What is the scale effect of globalization (Primbetova et al, 2022)?",
                Arrays.asList("It refers to the improvement of economic activity",
                        "It refers to the increase in aggregate level of natural resources and environmental degradation",
                        "It refers to the rise in environmental consciousness",
                        "It refers to the increase in private environmental spending"),
                "It refers to the increase in aggregate level of natural resources and environmental degradation"));

        // QUIZ 9
        questions.add(new QuizQuestion("How technologies impact on Politics?",
                Arrays.asList("Technology as a tool for political actors",
                        "Technology as a political topic and campaign platform",
                        "Technology as a tool for the public",
                        "All the mentioned"),
                "All the mentioned"));
        questions.add(new QuizQuestion("According to Aday how social media impact on democracy?",
                Arrays.asList("Promotes freedom of expression",
                        "Promotes hate and extreme views and in some cases violence",
                        "They allow for the rampant spread of misinformation",
                        "All the mentioned"),
                "Promotes freedom of expression"));
        questions.add(new QuizQuestion("What is Disinformation?",
                Arrays.asList("The intentionally false meaning, which actors spread and know to be false",
                        "An unfair negative attitude toward a social group or a member of that group",
                        "Is information that is false, but the person who is disseminating it believes that it is true",
                        "All the mentioned"),
                "The intentionally false meaning, which actors spread and know to be false"));
        questions.add(new QuizQuestion("What is the difference between cyber security and information security?",
                Arrays.asList("Cyber security usually deals with cybercrimes, information security involves the safety of computer systems",
                        "Cyber security is all about protecting data that is found in electronic form, information security means protecting information and information systems from unauthorized access",
                        "Cyber security provides confidentiality, integrity and availability of information, information security means protecting the data in cyberspace",
                        "Cyber security and information security are synonyms"),
                "Cyber security is all about protecting data that is found in electronic form, information security means protecting information and information systems from unauthorized access"));
        questions.add(new QuizQuestion("What does socio-political perspective means in information security?",
                Arrays.asList("The protection of national information assets, information systems, information",
                        "The protection of the national information environment and mass media from purposeful negative information",
                        "The protection from unauthorized access, use, disclosure, interruption, modification",
                        "The protection from unauthorized verification, data recording"),
                "The protection of the national information environment and mass media from purposeful negative information"));
        questions.add(new QuizQuestion("Why Social media have played a crucial role in political conflicts in the Middle East?",
                Arrays.asList("Facilitated the mobilization of demonstrators",
                        "Empowered political activists",
                        "Speeded-up the process of democratization",
                        "All the mentioned"),
                "All the mentioned"));
        questions.add(new QuizQuestion("According to Gunawan and Ratmono what had a significant negative impact on the Indonesian elections?",
                Arrays.asList("Hacking attacks",
                        "Disinformation and propaganda",
                        "Hoaxes and hate speech",
                        "All the mentioned"),
                "Hoaxes and hate speech"));
        questions.add(new QuizQuestion("The rank of Kazakhstan in Global cybersecurity index 2020?",
                Arrays.asList("5", "31", "115", "194"),
                "31"));
        questions.add(new QuizQuestion("What is the MAIN threat of cyberspace for Russia, Kazakhstan and China?",
                Arrays.asList("Destabilization of political environments by disinformation",
                        "Monopolization the market",
                        "The criminal purposes, including the commission of computer crimes and various types of fraud",
                        "Computer attacks on information infrastructure"),
                "Destabilization of political environments by disinformation"));
        questions.add(new QuizQuestion("According to Craig and Valeriano what is the most dominant actors in the cyberspace?",
                Arrays.asList("Non-governmental organizations",
                        "Private military organizations",
                        "Terrorist groups",
                        "States"),
                "States"));

        // QUIZ 10
        questions.add(new QuizQuestion("How many branches of power are there in Kazakhstan?",
                Arrays.asList("Two",
                        "Three",
                        "Four",
                        "Five"),
                "Three"));
        questions.add(new QuizQuestion("Which branch of power determines the main directions of domestic and foreign policy in Kazakhstan?",
                Arrays.asList("Legislative branch",
                        "Executive branch",
                        "Judicial branch",
                        "None of the above"),
                "Executive branch"));
        questions.add(new QuizQuestion("What is the role of the President of Kazakhstan?",
                Arrays.asList("Head of the legislative branch",
                        "Head of the executive branch",
                        "Head of the judicial branch",
                        "Head of the military"),
                "Head of the executive branch"));
        questions.add(new QuizQuestion("What are the names of the two chambers of the Parliament in Kazakhstan?",
                Arrays.asList("Senate and Assembly",
                        "Congress and Senate",
                        "Majilis and Senate",
                        "House of Representatives and Senate"),
                "Majilis and Senate"));
        questions.add(new QuizQuestion("How often are members of the Senate re-elected?",
                Arrays.asList("Every year",
                        "Every two years",
                        "Every three years",
                        "Every five years"),
                "Every three years"));
        questions.add(new QuizQuestion("How many deputies are there in the Majilis?",
                Arrays.asList("69",
                        "79",
                        "89",
                        "98"),
                "98"));
        questions.add(new QuizQuestion("What is the main characteristic of disenchanted citizens?",
                Arrays.asList("They actively participate in the political process.",
                        "They have positive views about democratic institutions.",
                        "They feel disillusioned with the political system.",
                        "They engage in online activism."),
                "They feel disillusioned with the political system."));
        questions.add(new QuizQuestion("Which group of citizens is less visible in their political engagement?",
                Arrays.asList("Nostalgic citizens.", "Enthusiastic citizens.", "Civic citizens.", "Stealth citizens."),
                "Stealth citizens."));
        questions.add(new QuizQuestion("What do civic citizens in Kazakhstan demonstrate?",
                Arrays.asList("Trust in the political system and its democratic credentials.",
                        "A strong longing for the past.",
                        "Optimism and eagerness towards community involvement.",
                        "A sense of apathy or disengagement from politics."),
                "Trust in the political system and its democratic credentials."));
        questions.add(new QuizQuestion("Which international organization is Kazakhstan a member of in its efforts to combat terrorism and extremism?",
                Arrays.asList("United Nations Security Council",
                        "Organization for Security and Cooperation in Europe (OSCE)",
                        "Shanghai Cooperation Organization (SCO)",
                        "World Trade Organization (WTO)"),
                "Shanghai Cooperation Organization (SCO)"));

        // политика.pdf
        questions.add(new QuizQuestion("What term refers to the method by which votes are translated into seats in an election?",
                Arrays.asList("Electoral system", "Voting system", "Representation system", "Proportional system"),
                "Electoral system"));
        questions.add(new QuizQuestion("What is the nature of the control a state exercises over a population?",
                Arrays.asList("Authoritarian control", "Coercive control", "Voluntary control", "Democratic control"),
                "Coercive control"));
        questions.add(new QuizQuestion("What is a unipolar system?",
                Arrays.asList("A system where two superpowers dominate", "A system with a single dominant power", "A system based on regional cooperation", "A system where no power dominates"),
                "A system with a single dominant power"));
        questions.add(new QuizQuestion("Which of the following best describes a bipolar system?",
                Arrays.asList("A system with multiple dominant powers", "A world with no clear power centers", "A rivalry between two superpowers", "The cooperation of several equal states"),
                "A rivalry between two superpowers"));
        questions.add(new QuizQuestion("How many branches of power are there in Kazakhstan?",
                Arrays.asList("Four", "Two", "Five", "Three"),
                "Three"));
        questions.add(new QuizQuestion("What is the main purpose of the system of checks and balances in government?",
                Arrays.asList("To concentrate power in one branch of government", "To ensure the dominance of the executive branch", "To limit the role of the judiciary branch", "To prevent abuses of power and maintain accountability"),
                "To prevent abuses of power and maintain accountability"));
        questions.add(new QuizQuestion("Which of the following is an example of a political institution?",
                Arrays.asList("Religious faith", "Trade union", "Family", "State"),
                "State"));
        questions.add(new QuizQuestion("What type of party typically has a large membership organized in local branches?",
                Arrays.asList("Elite party", "Mass party", "Cadre party", "Catch-all party"),
                "Mass party"));
        questions.add(new QuizQuestion("Which type of parties originated outside legislatures and aimed to achieve policy objectives?",
                Arrays.asList("Mass parties", "Catch-all parties", "Elite parties", "Cadre parties"),
                "Mass parties"));
        questions.add(new QuizQuestion("Who recognized the growing importance of political parties in modern politics?",
                Arrays.asList("Robert Michels", "Maurice Duverger", "Edmund Burke", "Moisei Ostrogorski"),
                "Maurice Duverger"));
        questions.add(new QuizQuestion("Which countries are examples of single-party or one-party states?",
                Arrays.asList("Saudi Arabia, China, and Cuba", "North Korea, China, and Cuba", "Saudi Arabia, North Korea, and China", "South Africa, North Korea, and China"),
                "North Korea, China, and Cuba"));
        questions.add(new QuizQuestion("Which theory proposes that the state is established by God(s)?",
                Arrays.asList("Patriarchal theory", "Voluntaristic theory", "Divine theory", "Coercive theory"),
                "Divine theory"));
        questions.add(new QuizQuestion("Which theory suggests that states emerged from family structures?",
                Arrays.asList("Patriarchal theory", "Divine theory", "Social contract theory", "Coercive theory"),
                "Patriarchal theory"));
        questions.add(new QuizQuestion("During which period did civil society in Kazakhstan experience significant development?",
                Arrays.asList("After independence", "1985-1991", "None of the above", "Before 1985"),
                "After independence"));
        questions.add(new QuizQuestion("In what way did ex-US President Barack Obama utilize technology for his presidential campaigns?",
                Arrays.asList("By using web-based campaigns and data analytics", "By blocking tech acquisitions for national security reasons", "By eliminating the use of technology in his campaigns", "By organizing mass protests via social media"),
                "By using web-based campaigns and data analytics"));
        questions.add(new QuizQuestion("What positive impact of social media did Nugroho and Syarief find in their study on contemporary Indonesia?",
                Arrays.asList("Weakening of socio-political dynamics", "Reduction in information dissemination", "Strengthening of democratic structures", "Decrease in political participation"),
                "Strengthening of democratic structures"));
        questions.add(new QuizQuestion("What impact do IT advancements have on civil liberties, according to the text?",
                Arrays.asList("They have no impact on civil liberties", "They strengthen civil liberties by promoting freedom of speech", "They enhance civil liberties by increasing access to information", "They put civil liberties at risk by endangering data privacy"),
                "They put civil liberties at risk by endangering data privacy"));
        questions.add(new QuizQuestion("What impact can westernization have on non-Western societies according to critics?",
                Arrays.asList("Improvement in local governance systems", "Loss of cultural identity and values", "Strengthening of traditional crafts", "Increase in economic independence"),
                "Loss of cultural identity and values"));
        questions.add(new QuizQuestion("Which form of democracy involves people voting directly on policy initiatives?",
                Arrays.asList("Hybrid democracy", "Oligarchic democracy", "Direct democracy", "Representative democracy"),
                "Direct democracy"));
        questions.add(new QuizQuestion("What term refers to the adoption of new technologies and economic development in societies?",
                Arrays.asList("Globalization", "Westernization", "Modernization", "Urbanization"),
                "Modernization"));
    }

    private void buildUI() {
        frame = new JFrame("Full Political Quiz");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 600);
        frame.setLayout(new BorderLayout());

        Color bgColor = new Color(45, 45, 45);
        Color textColor = Color.WHITE;
        Color buttonColor = new Color(70, 70, 70);
        Color selectedColor = new Color(100, 100, 100);

        questionLabel = new JLabel("Question");
        questionLabel.setFont(new Font("Arial", Font.BOLD, 18));
        questionLabel.setForeground(textColor);
        questionLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        questionLabel.setOpaque(true);
        questionLabel.setBackground(bgColor);
        frame.add(questionLabel, BorderLayout.NORTH);

        JPanel optionsPanel = new JPanel();
        optionsPanel.setLayout(new GridLayout(4, 1, 10, 10));
        optionsPanel.setBackground(bgColor);

        optionButtons = new JRadioButton[4];
        optionsGroup = new ButtonGroup();

        for (int i = 0; i < 4; i++) {
            optionButtons[i] = new JRadioButton();
            optionButtons[i].setFont(new Font("Arial", Font.PLAIN, 16));
            optionButtons[i].setForeground(textColor);
            optionButtons[i].setBackground(bgColor);
            optionButtons[i].setFocusPainted(false);
            optionButtons[i].setOpaque(true);
            optionButtons[i].setBorderPainted(true);
            optionButtons[i].setBorder(BorderFactory.createLineBorder(selectedColor));
            optionsGroup.add(optionButtons[i]);
            optionsPanel.add(optionButtons[i]);
        }
        optionsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        frame.add(optionsPanel, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBackground(bgColor);

        JPanel navPanel = new JPanel();
        navPanel.setBackground(bgColor);

        prevButton = new JButton("Previous");
        prevButton.setFont(new Font("Arial", Font.BOLD, 14));
        prevButton.setForeground(textColor);
        prevButton.setBackground(buttonColor);
        prevButton.setFocusPainted(false);
        prevButton.setBorder(BorderFactory.createLineBorder(selectedColor));
        prevButton.addActionListener(e -> {
            saveUserAnswer();
            if (currentQuestionIndex > 0) {
                currentQuestionIndex--;
                loadQuestion();
            }
        });
        navPanel.add(prevButton);

        nextButton = new JButton("Next");
        nextButton.setFont(new Font("Arial", Font.BOLD, 14));
        nextButton.setForeground(textColor);
        nextButton.setBackground(buttonColor);
        nextButton.setFocusPainted(false);
        nextButton.setBorder(BorderFactory.createLineBorder(selectedColor));
        nextButton.addActionListener(e -> {
            saveUserAnswer();
            checkAndProvideFeedback();
            currentQuestionIndex++;
            loadQuestion();
        });
        navPanel.add(nextButton);

        bottomPanel.add(navPanel, BorderLayout.EAST);
        frame.add(bottomPanel, BorderLayout.SOUTH);

        frame.getContentPane().setBackground(bgColor);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }


    private void loadQuestion() {
        if (currentQuestionIndex < totalQuestions) {
            optionsGroup.clearSelection();
            QuizQuestion current = questions.get(currentQuestionIndex);
            java.util.List<String> randomizedOptions = new ArrayList<>(current.options);
            Collections.shuffle(randomizedOptions);
            questionLabel.setText((currentQuestionIndex + 1) + ". " + current.question);
            for (int i = 0; i < 4; i++) {
                optionButtons[i].setText(randomizedOptions.get(i));
            }
            if (userAnswers[currentQuestionIndex] != null) {
                for (JRadioButton rb : optionButtons) {
                    if (rb.getText().equals(userAnswers[currentQuestionIndex])) {
                        rb.setSelected(true);
                        break;
                    }
                }
            }
        } else {
            finishQuiz();
        }
    }

    private void saveUserAnswer() {
        for (JRadioButton rb : optionButtons) {
            if (rb.isSelected()) {
                userAnswers[currentQuestionIndex] = rb.getText();
                return;
            }
        }
        userAnswers[currentQuestionIndex] = null;
    }

    private void checkAndProvideFeedback() {
        QuizQuestion current = questions.get(currentQuestionIndex);
        String selected = null;
        for (JRadioButton rb : optionButtons) {
            if (rb.isSelected()) {
                selected = rb.getText();
                break;
            }
        }
        if (selected == null) {
            return;
        }
        if (!selected.equals(current.correctAnswer)) {
            JOptionPane.showMessageDialog(frame, "Wrong! The correct answer is: " + current.correctAnswer, "Feedback", JOptionPane.INFORMATION_MESSAGE);
        } else {
            score++;
        }
    }

    private void finishQuiz() {
        JOptionPane.showMessageDialog(frame, "Quiz Over! Your score: " + score + " out of " + totalQuestions);
        saveResults();
        frame.dispose();
    }

    private void saveResults() {
        try (FileWriter writer = new FileWriter("quiz_results.txt", true)) {
            writer.write("Score: " + score + " out of " + totalQuestions + "\n");
            writer.write("Date: " + new Date() + "\n\n");
        } catch (IOException e) {
            System.err.println("Error saving results: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new FullPoliticalQuiz());
    }
}
