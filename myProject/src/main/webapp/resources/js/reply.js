
	/* REPLY */

	Handlebars.registerHelper("prettifyDate", function(timeValue){
		var dateObj = new Date(timeValue);
		var year = dateObj.getFullYear();
		var month = dateObj.getMonth() + 1;
		var date = dateObj.getDate();
		return year + "/" + month + "/" + date;
	});
	
	var printData = function(replyArr, target, templateObject) {
		
		var template = Handlebars.compile(templateObject.html());
		
		var html = template(replyArr);
		$(".replyLi").remove();
		target.html(html);
		
	}
	
	/* GET PAGE */
	
	var replyPage = 1;
	
	function getPage(pageInfo) {
		$.getJSON(pageInfo, function(data){
			
			printData(data.listReply, $("#repliesDiv"), $("#template"));
			
			printPaging(data.pageMaker, $(".pagination"));
			
			/*$("#replyCntSamll").html("[" + data.pageMaker.totalCount + "]");*/
		});
	}
	
	/* PageMaker */
	
	var printPaging = function(pageMaker, target) {
		
		var str = "";
		
		if(pageMaker.prev) {
			str += "<li><a href='" + (pageMaker.startPage - 1) + "'> << </a></li>";
		}
		
		for(var i = pageMaker.startPage, len = pageMaker.endPage; i <= len; i++) {
			var strClass = pageMaker.cri.page == i?'class=active' : '';
			str += "<li " + strClass + "><a href='" + i + "'>" + i + "</a></li>";
		}
		
		if(pageMaker.next) {
			str += "<li><a href='" + (pageMaker.endPage + 1) + "'> >> </a></li>";
		}
		
		target.html(str);
	}
	