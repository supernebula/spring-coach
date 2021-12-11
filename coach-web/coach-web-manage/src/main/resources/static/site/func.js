//获取当前URI的queryString
function getQueryString(name) {
    var reg = new RegExp('(^|&)' + name + '=([^&]*)(&|$)', 'i');
    var r = window.location.search.substr(1).match(reg);
    if (r != null) {
        return unescape(r[2]);
    }
    return null;
}

function handResponseStatus(statusCode){
    if(statusCode == 200) return;
    //token无效，退出登录
    if(statusCode == 428) {
        window.localStorage.removeItem("token");
        window.sessionStorage.removeItem("token");
        window.location.href = "/login";
    }
}