package ua.study.poject.cruise.util;

import static ua.study.poject.cruise.entity.Ticketclass.*;

/**
 * The class contains string constants that are used in different parts of the program
 */
public class StringStorage {

    // All
    public static final String USER_IN_SESSION = "currentuser"; // NOT LINKED FROM JSP!
    public static final String CONTROLLER = "/controller";
    public static final String BANDLE_MESSAGE = "message";

    // Headers
    public static final String LANGUAGE = "language";

    // Add ship services to system
    public static final String ADD_SHIP_SERVICES_TO_SYSTEM_FORM = "addshipservicestosystemForm";
    public static final String NEW_SERVICE_IN_SYSTEM = "newServiseInSystem";

    // Add/delete ship services to ship
    public static final String ADD_SHIP_SERVICE_TO_SHIP = "addshipservicetoship";
    public static final String SELECT_SHIP_SERVICES_FORM = "selectshipservicesForm";
    public static final String SELECTED_SHIP = "selectedship"; // +Create cruise, +Delete ship services from ship
    public static final String SELECTED_SERVICE = "selectedservice";
    public static final String PAYABLE = "payable";
    public static final String SHIP_SERVICE = "shipservice";

    // Change user role
    public static final String CHANGE_USER_ROLE_FORM = "changeuserroleForm";
    public static final String SELECTED_ROLE = "selectedrole";

    // Create cruise
    public static final String CREATE_CRUISE_FORM = "createcruiseForm";
    public static final String PRICE_FIRST_CLASS = "priceFirstClass";
    public static final String PRICE_SECOND_CLASS = "priceSecondClass";
    public static final String PRICE_THIRD_CLASS = "priceThirdClass";
    public static final String PRICE_FOURTH_CLASS = "priceFourthClass";

    public static final String SELECTED_PORT_START = "selectedportstart";
    public static final String DATE_START = "datestart";

    public static final String SELECTED_PORT_1 = "selectedport1";
    public static final String DATE_1_IN = "date1in";
    public static final String DATE_1_OUT = "date1out";

    public static final String SELECTED_PORT_2 = "selectedport2";
    public static final String DATE_2_IN = "date2in";
    public static final String DATE_2_OUT = "date2out";

    public static final String SELECTED_PORT_3 = "selectedport3";
    public static final String DATE_3_IN = "date3in";
    public static final String DATE_3_OUT = "date3out";

    public static final String SELECTED_PORT_4 = "selectedport4";
    public static final String DATE_4_IN = "date4in";
    public static final String DATE_4_OUT = "date4out";

    public static final String SELECTED_PORT_5 = "selectedport5";
    public static final String DATE_5_IN = "date5in";
    public static final String DATE_5_OUT = "date5out";

    public static final String SELECTED_PORT_6 = "selectedport6";
    public static final String DATE_6_IN = "date6in";
    public static final String DATE_6_OUT = "date6out";

    public static final String SELECTED_PORT_7 = "selectedport7";
    public static final String DATE_7_IN = "date7in";
    public static final String DATE_7_OUT = "date7out";

    public static final String SELECTED_PORT_STOP = "selectedportstop";
    public static final String DATE_STOP = "datestop";

    // Create excursion
    public static final String SELECTED_PORT = "selectedport";
    public static final String CREATE_EXCURSION_FORM = "createexcursionForm";
    public static final String EXCURSION_NAME = "excursionName";
    public static final String DESCRIPTION = "description";
    public static final String PRICE = "price";


    // Create port
    public static final String CREATE_PORT_FORM = "createportForm";
    public static final String COUNTRY = "country";
    public static final String CITY = "city";

    // Create ship
    public static final String CREATE_SHIP_FORM = "createshipForm";
    public static final String SHIP_NAME = "shipName";
    public static final String N_STAFF = "nStaff";
    public static final String N_FIRST_CLASS = "nFirstClass";
    public static final String N_SECOND_CLASS = "nSecondClass";
    public static final String N_THIRD_CLASS = "nThirdClass";
    public static final String N_FOURTH_CLASS = "nFourthClass";

    // Edit account and SignUp
    public static final String EDIT_ACCOUNT_FORM = "editaccountForm";
    public static final String LOGIN = "login"; // +SingIn(Login.jsp), +Change user role
    public static final String PASSWORD = "password"; // +SingIn(Login.jsp), +Change user role
    public static final String PASSWORD2 = "password2";
    public static final String FIRST_NAME = "firstname";
    public static final String SECOND_NAME = "secondname";
    public static final String EMAIL = "email";
    public static final String TEL = "tel";

    // SignIn
    public static final String SIGN_IN_FORM = "signinForm";

    // Logout
    public static final String ALL_CRUISES = "allCruises"; // NOT LINKED FROM JSP! +SignIn, +SignUp, +StartPage,  +ViewCruise
    public static final String ALL_TICKETCLASSES = "allTicketClasses"; // NOT LINKED FROM JSP! +StartPage

    // Manage bonuses
    public static final String ADD_BONUSES_FORM = "addbonusesForm";
    public static final String SELECTED_CRUISE = "selectedcruise";
    public static final String SELECTED_TICKETCLASS = "selectedticketclass";
    public static final String SELECTED_SHIPSERVICE_ID = "selectedshipserviceid";
    public static final String BONUSES = "bonuses";

    // SignUp (registration.jsp)
    public static final String SIGN_UP_FORM = "signupForm";

    // Start page
    public static final String SELECTED_PORT_ID = "selectedPortId";
    public static final String SELECTED_CRUISE_ID = "selectedCruiseId";

    // View excursion and view port
    public static final String EXCURSION_ID_TO_CART = "excursionIdToCart";
    public static final String VIEW_EXCURSION_ID = "viewExcursionId";


    // View cruise
    public static final String CRUISE_ID_TO_CART = "cruiseIdToCart";

    // AddToCart
    public static final String TICKETCLASS = "ticketclassForm";

    public static final String FIRST = TICKET_CLASS_FIRST;
    public static final String SECOND = TICKET_CLASS_SECOND;
    public static final String THIRD = TICKET_CLASS_THIRD;
    public static final String FOURTH = TICKET_CLASS_FOURTH;

    public static final String CRUISES_IN_CART = "cruisesInCart";
    public static final String TICKETCLASSES_IN_CART = "ticketclassesInCart";
    public static final String EXCURSIONS_IN_CART = "excurisionsInCart";

    // View cart
    public static final String DELETE_CRUISE_FROM_CART_FORM = "deletecruisefromcartForm";
    public static final String DELETE_EXCURSION_FROM_CART_FORM = "deleteexcursionfromcartForm";


}
