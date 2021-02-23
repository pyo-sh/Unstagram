import axios, { AxiosResponse } from 'axios';

const SERVER: string = "localhost:8080/";
const API_PREFIX: string = "user";

axios.defaults.baseURL = SERVER;

interface loginProp {
    userId: string,
    password: string
}

export async function loginUser({userId, password}: loginProp):Promise<Object> {
    const formData = new FormData();
    formData.append("userId", userId);
    formData.append("password", password);

    const response: AxiosResponse = await axios.post(
        "login", formData
    );

    return response.data;
}

// export async function getToken({}): Promise<Object>{
//     const formData = new FormData();
//     formData.append("");
// }