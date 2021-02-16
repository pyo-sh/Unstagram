import axios, { AxiosResponse } from 'axios';

const SERVER: string = "localhost:8080/";
const API_PREFIX: string = "user";

axios.defaults.baseURL = SERVER + API_PREFIX;

export async function loginUser():Promise<Object> {
    const formData = new FormData();

    const response: AxiosResponse = await axios.post(
        "", formData
    );

    return response.data;
}