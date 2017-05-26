/* korea_hiphop */

/* NEWS TABLE */
create table news_board(
	newsNo INT not null AUTO_INCREMENT,
	nTitle VARCHAR(200) not null,
	nContent TEXT null,
	writer VARCHAR(50) not null,
	ragDate TIMESTAMP not null default now(),
	viewCnt INT default 0,
	replyCnt INT default 0,
	recommend INT default 0,
	thumbnailImg VARCHAR(200),
	PRIMARY KEY (newsNo));

select * from news_board;

insert into news_board(nTitle, nContent, writer) values('제목입니다.', '내용입니다.', '이준호');

select * from news_board where newsNo = 1;

select newsNo, nTitle, nContent, writer, ragDate, viewCnt, replyCnt, recommend, thumbnailImg from news_board where newsNo > 0 order by newsNo desc, ragDate desc;

update news_board set nTitle = '제목입니다. 수정', nContent = '내용입니다. 수정' where newsNo = 1;

delete from news_board where newsNo = 1;

