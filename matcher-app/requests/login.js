const axios = require('axios').default;

async function loginRequest(userID){
    axios({
        method: 'post',
        url: 'http://localhost:8080/login',
        data: {
            user_id: userID,
        }
    }).then(response => {
        console.log(response);
        //res.send(response);
    }).catch(error => {
        console.log(error);
        //res.send(error)
    });
}

module.exports = {loginRequest};