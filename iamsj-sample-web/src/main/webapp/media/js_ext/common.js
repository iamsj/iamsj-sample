var local = window.location;
var basePathUrl = local.protocol + "//" + local.host;

/**
 * 格式化日期
 * 
 * @param data
 *            日期字符串
 * @returns {String} 返回格式化后的日期
 */
function formatDate(data) {
	if (data == ''||data.length == 0||data==undefined) {
		return '';
	}
	var d = new Date(data); // 创建一个Date对象
	var localTime = d.getTime();
	var localOffset = d.getTimezoneOffset() * 60000; // 获得当地时间偏移的毫秒数
	var utc = localTime + localOffset; // utc即GMT时间
	var offset = 8; // 设置时区
	var timeZone = utc + (3600000 * offset);

	var dateFormat = new Date(timeZone);

	var year = dateFormat.getFullYear();
	var month = addNum(dateFormat.getMonth() + 1);

	var day = addNum(dateFormat.getDate());

	var hour = addNum(dateFormat.getHours());
	var minute = addNum(dateFormat.getMinutes());
	var second = addNum(dateFormat.getSeconds());
	var nowdate = year + "-" + month + "-" + day + " " + hour + ":" + minute;
	+":" + second;
	function addNum(t) {
		if (t < 10) {
			return "0" + t;
		}
		return t;
	}

	return nowdate;
}