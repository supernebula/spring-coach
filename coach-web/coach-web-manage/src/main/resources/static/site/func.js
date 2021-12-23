//获取当前URI的queryString
function getQueryString(name) {
    var reg = new RegExp('(^|&)' + name + '=([^&]*)(&|$)', 'i');
    var r = window.location.search.substr(1).match(reg);
    if (r != null) {
        return unescape(r[2]);
    }
    return null;
}

function handResponseStatus(response){
    var statusCode = response.status;
    if(statusCode == 200) return;
    //token无效，退出登录
    if(statusCode == 401 || response.responseJSON.code == 428) {
        window.sessionStorage.removeItem("token");
        window.sessionStorage.removeItem("loginName");
        window.sessionStorage.removeItem("realName");
        window.location.href = "/login";
    }
}