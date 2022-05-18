import axios from 'axios'

const request = axios.create({
    // in local machine, use:
    // baseURL: 'http://localhost:9191/',
    // in replit, use:
    baseURL: 'https://springbootvuems-1.myron0220.repl.co',
    timeout: 5000
})

request.interceptors.request.use(config => {
    config.headers['Content-Type'] = 'application/json;charset=utf-8';

    // config.headers['token'] = user.token;  // set request header
    return config
}, error => {
    return Promise.reject(error)
});

request.interceptors.response.use(
    response => {
        let res = response.data;
        // if returned a file type
        if (response.config.responseType === 'blob') {
            return res
        }
        // otherwise, return json
        if (typeof res === 'string') {
            res = res ? JSON.parse(res) : res
        }
        return res;
    },
    error => {
        console.log('err' + error) // for debug
        return Promise.reject(error)
    }
)


export default request

