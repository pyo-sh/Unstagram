import axios, { AxiosResponse } from 'axios';

const SERVER: string = "http://localhost:8080";
const API_PREFIX: string = "user";

axios.defaults.baseURL = SERVER;

export async function getBoardList():Promise<Object> {
    const response: AxiosResponse = await axios.get("/boards");

    return response.data;
}