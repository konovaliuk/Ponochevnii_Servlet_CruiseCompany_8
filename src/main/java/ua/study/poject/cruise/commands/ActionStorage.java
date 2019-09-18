package ua.study.poject.cruise.commands;

import ua.study.poject.cruise.entity.Role;
import ua.study.poject.cruise.entity.User;
import ua.study.poject.cruise.util.StringStorage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

public class ActionStorage {

    private static final String UNSIGNED = "unsigned";

    public static final String COMMAND = "command";

    public final static String SIGN_IN = "signin";
    public final static String SIGN_UP = "signup";
    public final static String LOGOUT = "logout";
    public final static String EDIT_ACCOUNT = "editaccount";
    public final static String VIEW_MY_CART = "viewcart";
    public final static String VIEW_CRUISE = "viewcruise";
    public final static String VIEW_EXCURSION = "viewexcursion";
    public final static String VIEW_MY_CRUISES = "viewmycruises";
    public final static String VIEW_PORT = "viewport";
    public final static String DELETE_ALL = "deleteall";

    public final static String START_PAGE = "startpage";
    public final static String CHANGE_LANGUAGE = "changelanguage";
    public final static String PAY = "pay";
    public final static String DELETE_FROM_CART = "deletefromcart";
    public final static String ADD_TO_CART = "addtocart";
    public final static String CREATE_CRUISE = "createcruise";
    public final static String CREATE_PORT = "createport";
    public final static String CREATE_SHIP = "createship";
    public final static String CREATE_EXCURSION = "createexcursion";
    public final static String CHANGE_USER_ROLE = "changeuserrole";
    public final static String ADD_BONUSES = "addbonuses";
    public final static String DELETE_BONUSES = "deletebonuses";
    public final static String ADD_SHIP_SERVICES_TO_SYSTEM = "addshipservicestosystem";
    public final static String ADD_SHIP_SERVICES_TO_SHIP = "addshipservicetoship";
    public final static String DELETE_SHIP_SERVICES_FROM_SHIP = "deleteshipservicefromship";
    public final static String MY_ACCOUNT = "myaccount";


    private static HashMap<String, Action> generalAction = new HashMap<>();
    private static HashMap<String, Action> adminAction = new HashMap<>();
    private static HashMap<String, Action> managerAction = new HashMap<>();
    private static HashMap<String, Action> customerAction = new HashMap<>();

    static {
        generalAction.put(SIGN_IN, new SingIn());
        generalAction.put(SIGN_UP, new SingUp());
        generalAction.put(START_PAGE, new StartPage());
        generalAction.put(CHANGE_LANGUAGE, new ChangeLanguage());
        generalAction.put(VIEW_CRUISE, new ViewCruise());
        generalAction.put(VIEW_EXCURSION, new ViewExcursion());
        generalAction.put(VIEW_PORT, new ViewPort());
        generalAction.put(ADD_TO_CART, new SingIn());

        customerAction.put(EDIT_ACCOUNT, new EditAccount());
        customerAction.put(START_PAGE, new StartPage());
        customerAction.put(CHANGE_LANGUAGE, new ChangeLanguage());
        customerAction.put(MY_ACCOUNT, new MyAccountCustomer());
        customerAction.put(ADD_TO_CART, new AddToCart());
        customerAction.put(DELETE_FROM_CART, new DeleteFromCart());
        customerAction.put(PAY, new Pay());
        customerAction.put(LOGOUT, new Logout());
        customerAction.put(VIEW_MY_CART, new ViewCart());
        customerAction.put(VIEW_CRUISE, new ViewCruise());
        customerAction.put(VIEW_EXCURSION, new ViewExcursion());
        customerAction.put(VIEW_MY_CRUISES, new ViewMyCruises());
        customerAction.put(VIEW_PORT, new ViewPort());
        customerAction.put(DELETE_ALL, new DeleteAllFromCart());

        managerAction.put(EDIT_ACCOUNT, new EditAccount());
        managerAction.put(ADD_TO_CART, new AddToCart());
        managerAction.put(DELETE_FROM_CART, new DeleteFromCart());
        managerAction.put(PAY, new Pay());
        managerAction.put(LOGOUT, new Logout());
        managerAction.put(ADD_BONUSES, new AddBonuses());
        managerAction.put(DELETE_BONUSES, new DeleteBonuses());
        managerAction.put(START_PAGE, new StartPage());
        managerAction.put(CHANGE_LANGUAGE, new ChangeLanguage());
        managerAction.put(MY_ACCOUNT, new MyAccountManager());
        managerAction.put(VIEW_MY_CART, new ViewCart());
        managerAction.put(VIEW_CRUISE, new ViewCruise());
        managerAction.put(VIEW_EXCURSION, new ViewExcursion());
        managerAction.put(VIEW_MY_CRUISES, new ViewMyCruises());
        managerAction.put(VIEW_PORT, new ViewPort());
        managerAction.put(DELETE_ALL, new DeleteAllFromCart());

        adminAction.put(EDIT_ACCOUNT, new EditAccount());
        adminAction.put(ADD_TO_CART, new AddToCart());
        adminAction.put(DELETE_FROM_CART, new DeleteFromCart());
        adminAction.put(PAY, new Pay());
        adminAction.put(LOGOUT, new Logout());
        adminAction.put(CREATE_CRUISE, new CreateCruise());
        adminAction.put(CREATE_PORT, new CreatePort());
        adminAction.put(CREATE_SHIP, new CreateShip());
        adminAction.put(CREATE_EXCURSION, new CreateExcursion());
        adminAction.put(CHANGE_USER_ROLE, new ChangeUserRole());
        adminAction.put(START_PAGE, new StartPage());
        adminAction.put(CHANGE_LANGUAGE, new ChangeLanguage());
        adminAction.put(ADD_SHIP_SERVICES_TO_SYSTEM, new AddShipServicesToSystem());
        adminAction.put(ADD_SHIP_SERVICES_TO_SHIP, new AddShipServicesToShip());
        adminAction.put(DELETE_SHIP_SERVICES_FROM_SHIP, new DeleteShipServicesFromShip());
        adminAction.put(MY_ACCOUNT, new MyAccountAdmin());
        adminAction.put(VIEW_MY_CART, new ViewCart());
        adminAction.put(VIEW_CRUISE, new ViewCruise());
        adminAction.put(VIEW_EXCURSION, new ViewExcursion());
        adminAction.put(VIEW_MY_CRUISES, new ViewMyCruises());
        adminAction.put(VIEW_PORT, new ViewPort());
        adminAction.put(DELETE_ALL, new DeleteAllFromCart());
    }

    private static ActionStorage instance = null;

    private ActionStorage() {
    }

    public Action getAction(HttpServletRequest request) {

        String role = checkRoleBySession(request);

        String command = request.getParameter(COMMAND);
        if(command == null)
            return generalAction.get(START_PAGE);

        Action action;

        switch (role) {
            case Role.ROLE_CUSTOMER:
                action = customerAction.get(command);
                if (action != null)
                    return action;
                else return customerAction.get(START_PAGE);

            case Role.ROLE_MANAGER:
                action = managerAction.get(command);
                if (action != null)
                    return action;
                else return managerAction.get(START_PAGE);

            case Role.ROLE_ADMIN:
                action = adminAction.get(command);
                if (action != null)
                    return action;
                else return adminAction.get(START_PAGE);

            case UNSIGNED:
                action = generalAction.get(command);
                if (action != null)
                    return action;
                else return generalAction.get(START_PAGE);
            default:
                return generalAction.get(START_PAGE);
        }
    }

    /**
     * Find role by session
     *
     * @param request
     * @return
     */
    private String checkRoleBySession(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        String role;
        if (session == null)
            return UNSIGNED;
        User currentUser = (User) session.getAttribute(StringStorage.USER_IN_SESSION);
        if(currentUser == null)
            return UNSIGNED;
        role = currentUser.getRole().getRole();
        return role;
    }

    public static ActionStorage getInstance() {
        if (instance == null) {
            instance = new ActionStorage();
        }
        return instance;
    }

}
