package ua.epam.poject.cruise.commands;

import ua.epam.poject.cruise.entity.Role;
import ua.epam.poject.cruise.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

public class ActionStorage {

    private static final String UNSIGNED = "unsigned";

    private static final String COMMAND = "command";

    public final static String SIGN_IN = "signin";
    public final static String SIGN_UP = "signup";
    public final static String LOGOUT = "logout";
    public final static String EDIT_ACCOUNT = "editaccount";
    public final static String VIEW_CART = "viewcart";
    public final static String START_PAGE = "startpage";
    public final static String ERROR_PAGE = "errorpage";
    public final static String PAY = "pay";
    public final static String DELETE_FROM_CART = "deletefromcart";
    public final static String ADD_TO_CART = "addtocart";
    public final static String CREATE_CRUISE = "createcruise";
    public final static String CREATE_PORT = "createport";
    public final static String CREATE_SHIP = "createship";
    public final static String VIEW_EXCURSION_IN_PORTS = "viewexcursioninports";
    public final static String CREATE_EXCURSION = "createexcursion";
    public final static String CHANGE_USER_ROLE = "changeuserrole";
    public final static String ADD_BONUSES = "addbonuses";
    public final static String ADD_SHIP_SERVICES_TO_DB = "addshipservicestosystem";
    public final static String ADD_SHIP_SERVICES_TO_SHIP = "addshipservicetoship";
    public final static String MY_ACCOUNT = "myaccount";

    private static HashMap<String, Action> generalAction = new HashMap<String, Action>();
    private static HashMap<String, Action> adminAction = new HashMap<String, Action>();
    private static HashMap<String, Action> managerAction = new HashMap<String, Action>();
    private static HashMap<String, Action> customerAction = new HashMap<String, Action>();

    static {
        generalAction.put(SIGN_IN, new SingIn());
        generalAction.put(SIGN_UP, new SingUp());
        generalAction.put(VIEW_EXCURSION_IN_PORTS, new ViewExcursionsInPort());
        generalAction.put(START_PAGE, new StartPage());
        generalAction.put(ERROR_PAGE, new ErrorPage());
        generalAction.put(ERROR_PAGE, new ErrorPage());
        generalAction.put(MY_ACCOUNT, new MyAccountCustomer());

        adminAction.put(EDIT_ACCOUNT, new EditAccount());
        adminAction.put(VIEW_CART, new ViewCart());
        adminAction.put(ADD_TO_CART, new AddToCart());
        adminAction.put(DELETE_FROM_CART, new DeleteFromCart());
        adminAction.put(PAY, new Pay());
        adminAction.put(VIEW_EXCURSION_IN_PORTS, new ViewExcursionsInPort());
        adminAction.put(LOGOUT, new Logout());
        adminAction.put(CREATE_CRUISE, new CreateCruise());
        adminAction.put(CREATE_PORT, new CreatePort());
        adminAction.put(CREATE_SHIP, new CreateShip());
        adminAction.put(CREATE_EXCURSION, new CreateExcursion());
        adminAction.put(CHANGE_USER_ROLE, new ChangeUserRole());
        adminAction.put(START_PAGE, new StartPage());
        adminAction.put(ERROR_PAGE, new ErrorPage());
        adminAction.put(ADD_SHIP_SERVICES_TO_DB, new AddShipServicesToDB());
        adminAction.put(ADD_SHIP_SERVICES_TO_SHIP, new AddShipServicesToDB());
        adminAction.put(MY_ACCOUNT, new MyAccountAdmin());


        managerAction.put(EDIT_ACCOUNT, new EditAccount());
        managerAction.put(VIEW_CART, new ViewCart());
        managerAction.put(ADD_TO_CART, new AddToCart());
        managerAction.put(DELETE_FROM_CART, new DeleteFromCart());
        managerAction.put(PAY, new Pay());
        managerAction.put(VIEW_EXCURSION_IN_PORTS, new ViewExcursionsInPort());
        managerAction.put(LOGOUT, new Logout());
        managerAction.put(ADD_BONUSES, new AddBonuses());
        managerAction.put(START_PAGE, new StartPage());
        managerAction.put(ERROR_PAGE, new ErrorPage());
        managerAction.put(MY_ACCOUNT, new MyAccountManager());

    }

    private static ActionStorage instance = null;

    private ActionStorage() {
    }

    public Action getAction(HttpServletRequest request) {

        String role = chechRoleBySession(request);

        String command = request.getParameter(COMMAND);
        if(command == null)
            return generalAction.get(START_PAGE);

        Action action;

        switch (role) {
            case Role.ROLE_CUSTOMER:
                action = customerAction.get(command);
                if (action != null)
                    return action;
                else return generalAction.get(START_PAGE);

            case Role.ROLE_MANAGER:
                action = managerAction.get(command);
                if (action != null)
                    return action;
                else return generalAction.get(START_PAGE);

            case Role.ROLE_ADMIN:
                action = adminAction.get(command);
                if (action != null)
                    return action;
                else return generalAction.get(START_PAGE);

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
    private String chechRoleBySession(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        String role = null;
        if (session == null)
            return UNSIGNED;
        User currentUser = (User) session.getAttribute(StringConstantsStorage.userKeyInSession);
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
