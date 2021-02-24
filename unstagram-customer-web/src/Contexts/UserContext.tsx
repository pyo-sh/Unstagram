import { createContext, Dispatch, useReducer, useContext } from 'react';

// State ---------------------------------------------------------

export type User = {
    loading: boolean,
    isLoggedIn: boolean,
    data: {

    } | null,
    error: string | null
}

type UserState = User;

const initialState: User = {
    loading: false,
    isLoggedIn: false,
    data: null,
    error: null
}

const UserStateContext = createContext<UserState | undefined>(undefined);

// Action --------------------------------------------------------

type Action = 
    |   { type: 'LOGIN_USER'; }
    |   { type: 'LOGIN_USER_SUCCESS'; }
    |   { type: 'LOGIN_USER_FAILURE'; };

type UserDispatch = Dispatch<Action>;

const UserDispatchContext = createContext<UserDispatch | undefined>(undefined);

export function useUserState(){
    const state = useContext(UserStateContext);
    if(!state){
        throw new Error('UserProvider not Found');
    }
    return state;
}

// Reducer -------------------------------------------------------

function userReducer(state: UserState, action: Action): UserState {
    switch (action.type){
        case 'LOGIN_USER':
            return {
                ...state,
                loading: false
            };
        case 'LOGIN_USER_SUCCESS':
            return {
                ...state
            };
        case 'LOGIN_USER_FAILURE':
            return {
                ...state
            };
        default:
            throw new Error('Unhandled Action');
    }
}

export function useUserDispatch(){
    const dispatch = useContext(UserDispatchContext);
    if(!dispatch){
        throw new Error("UserProvider not Found");
    }
    return dispatch;
}

// Provider(Component) --------------------------------------------

export function UserContextProvider({ children }: { children: React.ReactNode }): JSX.Element{
    const [user, dispatch] = useReducer(userReducer, initialState);

    return (
        <UserDispatchContext.Provider value={dispatch}>
            <UserStateContext.Provider value={user}>
                { children }
            </UserStateContext.Provider>
        </UserDispatchContext.Provider>
    );
}