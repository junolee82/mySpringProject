
	function checkImageType(fileName) {
	
		var pattern = /jpg|gif|png|jpeg/i;
		
		return fileName.match(pattern);
		
	}
	
	function getFileInfo(fullName) {
		
		var fileName, imgsrc, getLink;
		var fileLink;
		
		if(checkImageType(fullName)) {
			
			fileLink = fullName.substr(14);
			
			imgsrc = "/displayFile?fileName=" + fullName;
			
			var front = fullName.substr(0,12);
			var end = fullName.substr(14);
			getLink = "/displayFile?fileName=" + front + end;
			
			titleImg = front + end;
			
		} else {
			
			fileLink = fullName.substr(12);
			
			imgsrc = "/resources/dist/img/file.png";			
			getLink = "displayFile?fileName=" + fullName;
			
		}
		
		fileName = fileLink.substr(fileLink.indexOf("_") + 1);
		
		return {fileName:fileName, imgsrc:imgsrc, getLink:getLink, fullName:fullName, titleImg:titleImg};
		
	}
	