package ua.study.poject.cruise.commands;

import ua.study.poject.cruise.entity.Role;
import ua.study.poject.cruise.entity.User;
import ua.study.poject.cruise.util.StringStorage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

/**
 * ActionStorage is singletone class that stores all the commands that the user can execute,
 * depending on his role
 */
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

    private static HashMap<String, String> generalAction = new HashMap<>();
    private static HashMap<String, String> customerAction = new HashMap<>();
    private static HashMap<String, String> managerAction = new HashMap<>();
    private static HashMap<String, String> adminAction = new HashMap<>();

    static {
        generalAction.put(SIGN_IN, "ua.study.poject.cruise.commands.SingIn");
        generalAction.put(SIGN_UP, "ua.study.poject.cruise.commands.SingUp");
        generalAction.put(START_PAGE, "ua.study.poject.cruise.commands.StartPage");
        generalAction.put(CHANGE_LANGUAGE, "ua.study.poject.cruise.commands.ChangeLanguage");
        generalAction.put(VIEW_CRUISE, "ua.study.poject.cruise.commands.ViewCruise");
        generalAction.put(VIEW_EXCURSION, "ua.study.poject.cruise.commands.ViewExcursion");
        generalAction.put(VIEW_PORT, "ua.study.poject.cruise.commands.ViewPort");
        generalAction.put(ADD_TO_CART, "ua.study.poject.cruise.commands.SingIn");

        customerAction.put(EDIT_ACCOUNT, "ua.study.poject.cruise.commands.EditAccount");
        customerAction.put(START_PAGE, "ua.study.poject.cruise.commands.StartPage");
        customerAction.put(CHANGE_LANGUAGE, "ua.study.poject.cruise.commands.ChangeLanguage");
        customerAction.put(MY_ACCOUNT, "ua.study.poject.cruise.commands.MyAccountCustomer");
        customerAction.put(ADD_TO_CART, "ua.study.poject.cruise.commands.AddToCart");
        customerAction.put(DELETE_FROM_CART, "ua.study.poject.cruise.commands.DeleteFromCart");
        customerAction.put(PAY, "ua.study.poject.cruise.commands.Pay");
        customerAction.put(LOGOUT, "ua.study.poject.cruise.commands.Logout");
        customerAction.put(VIEW_MY_CART, "ua.study.poject.cruise.commands.ViewCart");
        customerAction.put(VIEW_CRUISE, "ua.study.poject.cruise.commands.ViewCruise");
        customerAction.put(VIEW_EXCURSION, "ua.study.poject.cruise.commands.ViewExcursion");
        customerAction.put(VIEW_MY_CRUISES, "ua.study.poject.cruise.commands.ViewMyCruises");
        customerAction.put(VIEW_PORT, "ua.study.poject.cruise.commands.ViewPort");
        customerAction.put(DELETE_ALL, "ua.study.poject.cruise.commands.DeleteAllFromCart");

        managerAction.put(EDIT_ACCOUNT, "ua.study.poject.cruise.commands.EditAccount");
        managerAction.put(ADD_TO_CART, "ua.study.poject.cruise.commands.AddToCart");
        managerAction.put(DELETE_FROM_CART, "ua.study.poject.cruise.commands.DeleteFromCart");
        managerAction.put(PAY, "ua.study.poject.cruise.commands.Pay");
        managerAction.put(LOGOUT, "ua.study.poject.cruise.commands.Logout");
        managerAction.put(ADD_BONUSES, "ua.study.poject.cruise.commands.AddBonuses");
        managerAction.put(DELETE_BONUSES, "ua.study.poject.cruise.commands.DeleteBonuses");
        managerAction.put(START_PAGE, "ua.study.poject.cruise.commands.StartPage");
        managerAction.put(CHANGE_LANGUAGE, "ua.study.poject.cruise.commands.ChangeLanguage");
        managerAction.put(MY_ACCOUNT, "ua.study.poject.cruise.commands.MyAccountManager");
        managerAction.put(VIEW_MY_CART, "ua.study.poject.cruise.commands.ViewCart");
        managerAction.put(VIEW_CRUISE, "ua.study.poject.cruise.commands.ViewCruise");
        managerAction.put(VIEW_EXCURSION, "ua.study.poject.cruise.commands.ViewExcursion");
        managerAction.put(VIEW_MY_CRUISES, "ua.study.poject.cruise.commands.ViewMyCruises");
        managerAction.put(VIEW_PORT, "ua.study.poject.cruise.commands.ViewPort");
        managerAction.put(DELETE_ALL, "ua.study.poject.cruise.commands.DeleteAllFromCart");

        adminAction.put(EDIT_ACCOUNT, "ua.study.poject.cruise.commands.EditAccount");
        adminAction.put(ADD_TO_CART, "ua.study.poject.cruise.commands.AddToCart");
        adminAction.put(DELETE_FROM_CART, "ua.study.poject.cruise.commands.DeleteFromCart");
        adminAction.put(PAY, "ua.study.poject.cruise.commands.Pay");
        adminAction.put(LOGOUT, "ua.study.poject.cruise.commands.Logout");
        adminAction.put(CREATE_CRUISE, "ua.study.poject.cruise.commands.CreateCruise");
        adminAction.put(CREATE_PORT, "ua.study.poject.cruise.commands.CreatePort");
        adminAction.put(CREATE_SHIP, "ua.study.poject.cruise.commands.CreateShip");
        adminAction.put(CREATE_EXCURSION, "ua.study.poject.cruise.commands.CreateExcursion");
        adminAction.put(CHANGE_USER_ROLE, "ua.study.poject.cruise.commands.ChangeUserRole");
        adminAction.put(START_PAGE, "ua.study.poject.cruise.commands.StartPage");
        adminAction.put(CHANGE_LANGUAGE, "ua.study.poject.cruise.commands.ChangeLanguage");
        adminAction.put(ADD_SHIP_SERVICES_TO_SYSTEM, "ua.study.poject.cruise.commands.AddShipServicesToSystem");
        adminAction.put(ADD_SHIP_SERVICES_TO_SHIP, "ua.study.poject.cruise.commands.AddShipServicesToShip");
        adminAction.put(DELETE_SHIP_SERVICES_FROM_SHIP, "ua.study.poject.cruise.commands.DeleteShipServicesFromShip");
        adminAction.put(MY_ACCOUNT, "ua.study.poject.cruise.commands.MyAccountAdmin");
        adminAction.put(VIEW_MY_CART, "ua.study.poject.cruise.commands.ViewCart");
        adminAction.put(VIEW_CRUISE, "ua.study.poject.cruise.commands.ViewCruise");
        adminAction.put(VIEW_EXCURSION, "ua.study.poject.cruise.commands.ViewExcursion");
        adminAction.put(VIEW_MY_CRUISES, "ua.study.poject.cruise.commands.ViewMyCruises");
        adminAction.put(VIEW_PORT, "ua.study.poject.cruise.commands.ViewPort");
        adminAction.put(DELETE_ALL, "ua.study.poject.cruise.commands.DeleteAllFromCart");
    }

    private static ActionStorage instance = null;

    private ActionStorage() {}

    /**
     * getAction method returns an instance of the class that implements the Action interface.
     * Which specific object to return is decided based on which command the user wants to execute.
     * Information about this command is stored in the request parameter "command"
     * (request.getParameter ("command"))
     * This object will perform the corresponding action when the execute method is called.
     *
     * @param request
     * @return
     */
    public Action getAction(HttpServletRequest request) {

        String role = checkRoleBySession(request);

        String command = request.getParameter(COMMAND);
        if(command == null)
            return new StartPage();


        Action action;
        String actionString;

//        switch (role) {
//            case Role.ROLE_CUSTOMER:
//                action = customerAction.get(command);
//                return  action == null ? customerAction.get(START_PAGE) : action;
//
//            case Role.ROLE_MANAGER:
//                action = managerAction.get(command);
//                return  action == null ? managerAction.get(START_PAGE) : action;
//
//            case Role.ROLE_ADMIN:
//                action = adminAction.get(command);
//                return  action == null ? adminAction.get(START_PAGE) : action;
//
//            case UNSIGNED:
//                action = generalAction.get(command);
//                return  action == null ? generalAction.get(START_PAGE) : action;
//
//            default:
//                return generalAction.get(START_PAGE);
//        }

        switch (role) {
            case Role.ROLE_CUSTOMER:
                actionString = customerAction.get(command);
                break;
            case Role.ROLE_MANAGER:
                actionString = managerAction.get(command);
                break;
            case Role.ROLE_ADMIN:
                actionString = adminAction.get(command);
                break;
            case UNSIGNED:
                actionString = generalAction.get(command);
                break;
            default:
                actionString = generalAction.get(START_PAGE);
        }
        if (actionString == null) {
            customerAction.get(START_PAGE);
        }
        try {
            action = (Action)Class.forName(actionString).getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            action = new StartPage();
        }
        return action;
    }

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
