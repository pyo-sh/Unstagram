export default function createAsyncDispatcher(type: string, promiseFn: Function): Function {
    const SUCCESS: string = `${type}_SUCCESS`;
    const FAILURE: string = `${type}_FAILURE`;

    return async function actionHandler(dispatch: Function, ...rest: Array<any>) {
        dispatch({ type });
        try {
            const data = await promiseFn(...rest);
            dispatch({
                type: SUCCESS,
                data
            });
        } catch (e) {
            dispatch({
                type: FAILURE,
                error: e
            });
        }
    }
}