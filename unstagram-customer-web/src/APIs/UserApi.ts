import axios, { AxiosResponse } from 'axios';

const SERVER: string = "localhost:8080/";
const API_PREFIX: string = "user";

axios.defaults.baseURL = SERVER;

interface loginProp {
    userId: string,
    password: string
}

export async function loginUser({userId, password}: loginProp):Promise<Object> {
    const response: AxiosResponse = await axios.post(
        "login", {userId, password}
    );

    return response.data;
}

// export async function getToken({}): Promise<Object>{
//     const formData = new FormData();
//     formData.append("");
// }