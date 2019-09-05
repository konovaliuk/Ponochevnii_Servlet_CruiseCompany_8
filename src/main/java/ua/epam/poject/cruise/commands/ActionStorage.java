package ua.epam.poject.cruise.commands;

import ua.epam.poject.cruise.entity.Role;
import ua.epam.poject.cruise.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

public class ActionStorage {

    private static final String UNSIGNED = "unsigned";

    private static final String COMMAND = "command";

    private final static String SIGN_IN = "signin";
    private final static String SIGN_UP = "signup";
    private final static String LOGOUT = "logout";
    private final static String EDIT_ACCOUNT = "editaccount";
    private final static String VIEW_CART = "viewcart";
    private final static String START_PAGE = "startpage";
    private final static String ERROR_PAGE = "errorpage";
    private final static String PAY = "pay";
    private final static String DELETE_FROM_CART = "deletefromcart";
    private final static String ADD_TO_CART = "addtocart";
    private final static String CREATE_CRUISE = "createcruise";
    private final static String CREATE_PORT = "createport";
    private final static String CREATE_SHIP = "createship";
    private final static String VIEW_EXCURSION_IN_PORTS = "viewexcursioninports";
    private final static String CREATE_EXCURSION = "createexcursion";
    private final static String CHANGE_USER_ROLE = "changeuserrole";
    private final static String MANAGE_BONUSES = "managebonuses";
    private final static String ADD_SHIP_SERVICES_TO_SYSTEM = "addshipservicestosystem";
    private final static String ADD_SHIP_SERVICES_TO_SHIP = "addshipservicetoship";
    private final static String DELETE_SHIP_SERVICES_FROM_SHIP = "deleteshipservicefromship";
    private final static String MY_ACCOUNT = "myaccount";

    private static HashMap<String, Action> generalAction = new HashMap<>();
    private static HashMap<String, Action> adminAction = new HashMap<>();
    private static HashMap<String, Action> managerAction = new HashMap<>();
    private static HashMap<String, Action> customerAction = new HashMap<>();

    static {
        generalAction.put(SIGN_IN, new SingIn());
        generalAction.put(SIGN_UP, new SingUp());
        generalAction.put(VIEW_EXCURSION_IN_PORTS, new ViewExcursionsInPort());
        generalAction.put(START_PAGE, new StartPage());
        generalAction.put(ERROR_PAGE, new ErrorPage());


        customerAction.put(EDIT_ACCOUNT, new EditAccount());
        customerAction.put(ERROR_PAGE, new ErrorPage());
        customerAction.put(START_PAGE, new StartPage());
        customerAction.put(MY_ACCOUNT, new MyAccountCustomer());
        customerAction.put(VIEW_CART, new ViewCart());
        customerAction.put(ADD_TO_CART, new AddToCart());
        customerAction.put(DELETE_FROM_CART, new DeleteFromCart());
        customerAction.put(PAY, new Pay());
        customerAction.put(VIEW_EXCURSION_IN_PORTS, new ViewExcursionsInPort());
        customerAction.put(LOGOUT, new Logout());

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
        adminAction.put(ADD_SHIP_SERVICES_TO_SYSTEM, new AddShipServicesToSystem());
        adminAction.put(ADD_SHIP_SERVICES_TO_SHIP, new AddShipServicesToShip());
        adminAction.put(DELETE_SHIP_SERVICES_FROM_SHIP, new DeleteShipServicesFromShip());
        adminAction.put(MY_ACCOUNT, new MyAccountAdmin());


        managerAction.put(EDIT_ACCOUNT, new EditAccount());
        managerAction.put(VIEW_CART, new ViewCart());
        managerAction.put(ADD_TO_CART, new AddToCart());
        managerAction.put(DELETE_FROM_CART, new DeleteFromCart());
        managerAction.put(PAY, new Pay());
        managerAction.put(VIEW_EXCURSION_IN_PORTS, new ViewExcursionsInPort());
        managerAction.put(LOGOUT, new Logout());
        managerAction.put(MANAGE_BONUSES, new ManageBonuses());
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
        String role;
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
