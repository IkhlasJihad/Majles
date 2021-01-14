--Data for testing
-- It's written in Arabic because users of the system may have no English background, 
-- The learning community is Arabic as well.
insert into region values ('265', 'ÇáæÓØì');
insert into region values ('260', 'ÛÒÉ');
insert into region values ('270', 'ÎÇä íæäÓ');
insert into region values ('255', 'ÔãÇá ÛÒÉ');
insert into region values ('275', 'ÑİÍ');
insert into region values ('150', 'ÇáÎáíá');
insert into region values ('115', 'ÑÇã Çááå');

insert into category values (default, 'ÚŞíÏÉ'); --1
insert into category values (default, 'İŞå'); --2
insert into category values (default, 'ÓíÑ æ ÊÑÇÌã');--3
insert into category values (default, 'ÊİÓíÑ');--4
insert into category values (default, 'ÃÕæá İŞå');--5
insert into category values (default, 'äÍæ');--6
insert into category values (default, 'ÈáÇÛÉ');--7
insert into category values (default, 'ÍÏíË');--8
insert into category values (default, 'ÃÏÈ');--9
insert into category values (default, 'ÊÌæíÏ æ ŞÑÇÁÇÊ');--10
insert into category values (default, ' ÂÏÇÈ æ ÑŞÇÆŞ');--11
insert into book values(default,'ÊİÓíÑ ÇÈä ßËíÑ' 
						,'ãßÊÈÉ ÇáÃãá', 
						25.4);--10
insert into book(book_id, title) values(default,'ÇáÊÍÑíÑ æÇáÊäæíÑ');--11
insert into book values(default,'ÃÏÈ ÇáÏíä æÇáÏäíÇ'
						,'ãßÊÈÉ ÏÇÑ ÇáÓáÇã', 
						7.4); --12
insert into book(book_id, title) values(default,'ÇáÊİÓíÑ ÇáæÓíØ');	--13						
insert into book(book_id, title) values (default,'ÊİÓíÑ ÇáãäÇÑ'); --14
insert into book(book_id, title) values (default,'ãæØÃ ÇáÅãÇã ãÇáß'); -- 15
insert into book(book_id, title) values (default,'ÇáãÓÊÏÑß Úáì ÇáÕÍíÍíä');--16					
insert into book(book_id, title) values (default, 'ÍÏíË ÇáŞÑÂä Úä ÇáŞÑÂä');--17
insert into book(book_id, title) values(default, 'ÚãÏÉ ÇáŞÇÑí ÔÑÍ ÇáÈÎÇÑí');--18
insert into book(book_id, title) values (default, 'ÍÏíË ÇáŞÑÂä Úä ÇáŞÑÂä');--19
insert into book(book_id, title) values (default, 'ÇáÊãåíÏ İí Úáã ÇáÊÌæíÏ');--20
insert into book(book_id, title) values (default, 'ÇáÈÑåÇä İí ÊÌæíÏ ÇáŞÑÂä æÑÓÇáÉ İí İÖÇÆá ÇáŞÑÂä'); --21
insert into book values (default, 'ÇáãÛäí İí Úáã ÇáÊÌæíÏ'
						,'ãßÊÈÉ ÓãíÑ ãäÕæÑ', 
						3.0); --22
insert into book values (default, 'ãĞßÑÇÊ İí ãäÇÒá ÇáÕÏíŞíä æÇáÑÈÇäííä  - ãä ÎáÇá ÇáäÕæÕ æÍßã ÇÈä ÚØÇÁ Çááå ÇáÓßäÏÑí'
						,'ãßÊÈÉ ÏÇÑ ÇáÓáÇã', 
						8.71);		--23			
insert into book values (default, 'ÇáÓíÑÉ ÇáäÈæíÉ - ÓíÑÉ ÇÈä åÔÇã'
  					   ,'ãßÊÈÉ ÏÇÑ ÇáÓáÇã ', 
						23.0);--24
insert into book(book_id, title) values (default, 'ÍÇÔíÉ ÇáÓäÏí Úáì ÕÍíÍ ÇáÈÎÇÑí'); --25
insert into book(book_id,title) values(default, 'ÇáãäÍ ÇáÅáåíÉ İí ÌãÚ ÇáŞÑÇÁÇÊ ÇáÓÈÚ ãä ØÑíŞ ÇáÔÇØÈíÉ');--26
insert into book(book_id,title) values(default, 'ÇáÓÈÚÉ İí ÇáŞÑÇÁÇÊ');--27



insert into author values (default, 'ãÍãÏ ÇáÓäÏí ÃÈæ ÇáÍÓä');	--1				
insert into author values (default, 'ÈÏÑ ÇáÏíä ÇáÚíäí');		--2			
insert into author values (default, 'ÇÈä ÚÇÔæÑ');--3
insert into author values (default, 'ãÍãÏ ÓíÏ ØäØÇæí');--4					
insert into author values (default, 'ãÍãÏ ÑÔíÏ ÑÖÇ');--5
insert into author values (default, 'ãÍãÏ ÇáÑÇæí');--6
insert into author values (default, 'ÃÈæ ÇáÍÓä ÇáãÇæÑÏí');--7
insert into author values (default, 'ÇáäíÓÇÈæÑí');--8
insert into author values (default, 'ÇÈä ÇáÌÒÑí');--9
insert into author values (default, 'ãÍãÏ ÇáÕÇÏŞ ŞãÍÇæí');--10
insert into author values (default, 'ÚÈÏ ÇáÑÍãä ÇáÌãá');--11
insert into author values (default, 'ÚÈÏ Çáãáß Èä åÔÇã ÇáãÚÇİÑí'); --12
insert into author values (default, 'ÓÚíÏ Íæì'); --13
insert into author values(default, 'ãÍãÏ ÇáÚáãí'); --14
insert into author values(default, 'ÇÈä ãÌÇåÏ');--15
insert into author values (default, 'ãÇáß Èä ÃäÓ');	--16	
			
insert into  category_book values ('3','12');
insert into  category_book values ('2','15');
insert into  category_book values ('4','10');
insert into  category_book values ('4','11');
insert into  category_book values ('4','13');
insert into  category_book values ('4','14');
insert into  category_book values ('8','16');
insert into  category_book values ('8','18');
insert into  category_book values ('8','25');
insert into  category_book values ('11','12');
insert into  category_book values ('11','23');
insert into  category_book values ('10','20');
insert into  category_book values ('10','22');
insert into  category_book values ('10','26');
insert into  category_book values ('10','27');

insert into author_book values('16','15');
insert into author_book values('1','25');
insert into author_book values('2','18');
insert into author_book values('3','11');
insert into author_book values('4','13');
insert into author_book values('5','14');
insert into author_book values('6','19');
insert into author_book values('7','12');
insert into author_book values('8','16');
insert into author_book values('9','20');
insert into author_book values('10','21');
insert into author_book values('11','22');
insert into author_book values('12','24');
insert into author_book values('13','23');
insert into author_book values('14','26');
insert into author_book values('15','27');

insert into sheikh(sh_id,"name",gender, email, region_code) values('22','ÚãÑ ÇáØáÇáŞÉ','M','Domar@qraat.com','265' ) ;
insert into sheikh(sh_id,"name",gender,region_code) values('24','åÇäí ÇáÚáí','M','260' ) ;
insert into sheikh(sh_id,"name",gender,region_code) values('245','ÓáãÇä ÇáÏÇíÉ','M','260' ) ;
insert into sheikh(sh_id,"name",gender) values('352','ÃÍãÏ ÚÈÏ ÇáãäÚã','M') ;
insert into sheikh(sh_id,"name",gender) values('321','ÓÚíÏ Çáßãáí','M') ;

insert into majles (maj_id,sh_id,place,name,subject) values('101', '22','Zoom', 
		'ÇáãÌáÓ ÇáÓÇÏÓ áÊÚáã ÇáÌãÚ Èíä ÇáŞÑÇÁÇÊ ÇáÓÈÚ', 
		'ÊÚáã ÇáŞÑÇÁÇÊ');

insert into majles(maj_id,sh_id,place,name,subject) values('102', '245','ar.islamway.net', 
		'ÔÑÍ ßÊÇÈ ÇáÑŞÇŞ ãä ÕÍíÍ ÇáÈÎÇÑí', 
		'ÊÏÇÑÓ ÇáÍÏíË ÇáäÈæí ÇáÔÑíİ');	

insert into majles(maj_id,sh_id,place,name,subject) values('103', '321','Zoom', 
		'ßÑÓí ÇáÅãÇã ãÇáß', 
		'ÇáİŞå ÇáãÇáßí');
insert into majles(maj_id,sh_id,place,name,subject) values('104', '321','Zoom', 
		'ÔÑÍ ŞÕíÏÉ ÚäæÇä ÇáÍßã áÃÈí ÇáİÊÍ ÇáÈÓÊí', 
		'ÇáÃÏÈ ÇáÚÑÈí');	
insert into elucidates values('103','15');
insert into elucidates values('102','25');
insert into elucidates values('102','18');
	
insert into student values(default, 'ÃÓíÏ',
	'ÌåÇÏ',
	'ÃÍãÏ',
	'ÃÍãÏ', 'M', '265');
insert into student values(default, 'İÇÏí',
	'ãäĞÑ',
	'Úáí',
	'ÚÒÊ', 'M', '265');
insert into student values(default, 'ÅíÇÏ',
	'ãÕØİì',
	'ÊÍÓíä',
	'ÇáãØÑí', 'M', '265');
insert into student values(default, 'ÅíåÇÈ',
	'Øå',
	'ÊÍÓíä',
	'ÇáãØÑí', 'M', '265');
insert into student values(default, 'ãæÓì',
	'ÚãÑ',
	'ãæÓì',
	'ÇáãØÑí', 'M', '265');
insert into student values(default, 'ŞÕí',
	'ÚãÑ',
	'ÃÍãÏ',
	'ÇáãØÑí', 'M', '265');
insert into student values(default, 'ÃÍãÏ',
	'ÓÇãí',
	'ÛÓÇä',
	'ÃÈæ ÓæíÑÍ', 'M', '265');
insert into student values(default, 'ÅíÇÏ',
	'ÃÓÚÏ',
	'ÛÓÇä',
	'ÃÈæ ÓæíÑÍ', 'M', '265');

insert into student  values(default, 'æÇÆá',
	'ÃÍãÏ',
	'ãÍãÏ'
	,'ÍãÏ', 'M', '265');
insert into student values(default, 'ÑÇãí',
	'ÃÍãÏ',
	'ãÍãÏ'
	,'ÍãÏ', 'M', '265');
insert into student values(default, 'ÌåÇÏ',
	'Úáí',
	'ãÍãÏ'
	,'ÍãÏ', 'M', '265');
insert into student values(default, 'ÚÏí',
	'ÑÇÆÏ',
	'ãÑÇÏ',
	'æÇÏí', 'M', '265');
insert into student values(default, 'ãÑÇÏ',
	'ÑÇÆÏ',
	'ÊæİíŞ',
	'æÇÏí', 'M', '265');
insert into student values(default, 'ãÍãÏ',
	'Úáí',
	'ÓíÏ',
	'ÍãÏ', 'M', '265');
insert into student values(default, 'ÓÇãí',
	'ÑÇÆÏ',
	'ÊæİíŞ',
	'ÇáÏåÔÇä', 'M', '260');
insert into student values(default, 'ÃÍãÏ',
	'ãÍãæÏ',
	'ÃÍãÏ',
	'ÍÌæ', 'M', '260');
insert into student values(default, 'ÈíÇä',
	'ÑÇÆÏ',
	'ÊæİíŞ',
	'ãæÇİí', 'M', '260');
insert into student values(default, 'ÅÓáÇã',
	'ÑÇÆÏ',
	'ÊæİíŞ',
	'ãæÇİí', 'M', '260');
insert into student values(default, 'äÏì',
	'ãÍãÏ',
	'ÃÓÇãÉ',
	'ãæÇİí', 'F', '260');
insert into student values(default, 'Ããá',
	'Úáí',
	'ÑÃİÊ',
	'ÇáãØÑí', 'F', '260');
insert into student values(default, 'ÓãíÉ',
	'æÓÇã',
	'ÑÇãí',
	'ÅÈÑÇåíã', 'F', '260');
insert into student values(default, 'ÓÌì',
	'æÓÇã',
	'ÑÇãí',
	'ÅÈÑÇåíã', 'F', '260');
insert into student values(default, 'áíáì',
	'ÃÓÇãÉ',
	'ãÍãÏ',
	'ÇáÓíÏ', 'F', '265'); --23
insert into student values(default, 'ÑÔÇ',
	'ÃÍãÏ',
	'ãÍãÏ',
	'ÇáÊŞí', 'F', '265'); --24
insert into student values(default, 'ÅíãÇä',
	'ÃÍãÏ',
	'æÓÇã',
	'ÇáÊŞí', 'F', '265'); --25
insert into student values(default, 'æÚÏ',
	'áÄí',
	'ÌæÇÏ',
	'ÌæÏÉ', 'F', '265');--26

insert into joins values('1423','101',current_date);
insert into joins values('1424','101',current_date);
insert into joins values('1425','101',current_date);
insert into joins values('1426','101',current_date);
insert into joins values('1419','101',current_date);

insert into joins values('1401','102','2020-03-12');
insert into joins values('1407','102','2020-03-12');
insert into joins values('1408','102','2020-03-12');
insert into joins values('1404','102','2020-03-12');
insert into joins values('1405','102','2020-03-16');
insert into joins values('1406','102','2020-03-12');
insert into joins values('1403','102','2020-03-12');
insert into joins values('1402','102','2020-03-16');
insert into joins values('1422','102','2020-03-12');
insert into joins values('1421','102','2020-03-15');
insert into joins values('1420','102','2020-03-17');



/*insert into volunteer values('ahmad01', 'ÃÓíÏ ÃÍãÏ', 
							 '265');
insert into responsible_for values('ahmad01', '101');*/							
insert into volunteer values('emyw', 'ÅíãÇä', 
							 '265');
insert into responsible_for values('emyw', '101');
insert into responsible_for values('emyw', '104');
insert into volunteer values('mahmoud86', 'ãÍãæÏ', 
							 '260');							
insert into responsible_for values('mahmoud86', '102');							

insert into lecture(lec_id,maj_id,title,"date","time") values('1','102','ãŞÏãÉ',
			'2020-03-15','ÈÚÏ ÇáÚÕÑ'); -- the default place is that of the majles.
insert into lecture(lec_id,maj_id,title,"date","time") values('2','102','æŞİÇÊ ãÚ ÓíÑÉ ÇáÅãÇã ÇáÈÎÇÑí',
			'2020-03-17','ÈÚÏ ÇáÚÕÑ');
insert into lecture(lec_id,maj_id,title,"date","time") values('3','102','äÚãÊÇä ãÛÈæä İíåãÇ ßËíÑ ãä ÇáäÇÓ',
			'2020-03-22','ÈÚÏ ÇáÚÕÑ');		
insert into lecture(lec_id,maj_id,title,"date","time") values('4','102','ÇáÕÍÉ æÇáİÑÇÛ',
			'2020-03-24','ÈÚÏ ÇáÚÕÑ');							
insert into lecture(lec_id,maj_id,title,"date","time") values('5','102','áÇ ÚíÔ ÅáÇ ÚíÔ ÇáÂÎÑÉ',
			'2020-03-29','ÈÚÏ ÇáÚÕÑ');					
insert into lecture(lec_id,maj_id,title,"date","time") values('6','102','ãËá ÇáÏäíÇ İí ÇáÂÎÑÉ',
			'2020-03-31','ÈÚÏ ÇáÚÕÑ');							
insert into lecture(lec_id,maj_id,title,"date","time") values('7','102','ÚæÇÆŞ ÇáÇÓÊŞÇãÉ',
			'2020-04-05','ÈÚÏ ÇáÚÕÑ');							
insert into lecture(lec_id,maj_id,title,"date","time") values('8','102','ÛÑÈå ÇáÏíä',
			'2020-04-07','ÈÚÏ ÇáÚÕÑ');							
insert into lecture(lec_id,maj_id,title,"date","time") values('9','102','ÇáÑÌÇÁ ãÚ ÇáÎæİ',
			'2020-04-12','ÈÚÏ ÇáÚÕÑ');							
insert into lecture(lec_id,maj_id,title,"date","time") values('10','102','ÇáÃãá æØæá ÇáÚãÑ',
			'2020-04-14','ÈÚÏ ÇáÚÕÑ');							
insert into lecture(lec_id,maj_id,title,"date","time") values('11','102','ÇáÚÒáÉ ÑÇÍÉ ãä ÎáÇØ ÇáÓæÁ',
			'2020-04-19','ÈÚÏ ÇáÚÕÑ');							
insert into lecture(lec_id,maj_id,title,"date","time") values('12','102','ãä ÃÍÈ áŞÇÁ Çááå ÃÍÈ Çááå áŞÇÁå',
			'2020-04-21','ÈÚÏ ÇáÚÕÑ');	
select default_placeAll('102');		
		
insert into attends values('1401','1');
insert into attends values('1407','1');
insert into attends values('1402','1');
insert into attends values('1403','1');
insert into attends values('1408','1');
insert into attends values('1405','1');
insert into attends values('1406','1');

insert into attends values('1401','3');
insert into attends values('1407','3');
insert into attends values('1408','3');
insert into attends values('1403','3');
insert into attends values('1420','3');
insert into attends values('1422','3');
insert into attends values('1404','3');
insert into attends values('1405','3');
insert into attends values('1406','3');

insert into attends values('1401','4');
insert into attends values('1407','4');
insert into attends values('1403','4');
insert into attends values('1421','4');
insert into attends values('1422','4');
insert into attends values('1404','4');
insert into attends values('1405','4');
insert into attends values('1406','4');


insert into attends values('1407','5');
insert into attends values('1408','5');
insert into attends values('1403','5');
insert into attends values('1420','5');
insert into attends values('1421','5');
insert into attends values('1422','5');
insert into attends values('1404','5');
insert into attends values('1402','5');
insert into attends values('1405','5');
insert into attends values('1406','5');

insert into attends values('1401','6');
insert into attends values('1408','6');
insert into attends values('1403','6');
insert into attends values('1420','6');
insert into attends values('1421','6');
insert into attends values('1422','6');
insert into attends values('1404','6');
insert into attends values('1402','6');

insert into attends values('1401','7');
insert into attends values('1407','7');
insert into attends values('1408','7');
insert into attends values('1403','7');
insert into attends values('1420','7');
insert into attends values('1421','7');
insert into attends values('1422','7');
insert into attends values('1404','7');
insert into attends values('1402','7');
insert into attends values('1405','7');

insert into attends values('1401','8');
insert into attends values('1407','8');
insert into attends values('1408','8');
insert into attends values('1403','8');
insert into attends values('1420','8');
insert into attends values('1421','8');
insert into attends values('1422','8');
insert into attends values('1404','8');
insert into attends values('1402','8');
insert into attends values('1405','8');

insert into attends values('1401','9');
insert into attends values('1407','9');
insert into attends values('1408','9');
insert into attends values('1403','9');
insert into attends values('1420','9');
insert into attends values('1421','9');
insert into attends values('1422','9');
insert into attends values('1404','9');
insert into attends values('1402','9');
insert into attends values('1405','9');
insert into attends values('1406','9');

insert into attends values('1401','10');
insert into attends values('1407','10');
insert into attends values('1408','10');
insert into attends values('1403','10');
insert into attends values('1420','10');
insert into attends values('1421','10');
insert into attends values('1422','10');
insert into attends values('1404','10');
insert into attends values('1402','10');
insert into attends values('1405','10');
insert into attends values('1406','10');


insert into attends values('1401','12');
insert into attends values('1407','12');
insert into attends values('1408','12');
insert into attends values('1420','12');
insert into attends values('1421','12');
insert into attends values('1422','12');
insert into attends values('1402','12');
insert into attends values('1405','12');
insert into attends values('1406','12');

insert into lecture(lec_id,maj_id,title,"date","time") values('1041','104','ÒíÇÏÉ ÇáãÑÁ İí ÏäíÇå äŞÕÇä',
			'2020-05-10','ÈÚÏ ÇáÚÔÇÁ');	
insert into lecture(lec_id,maj_id,title,"date","time") values('1042','104','ãä íÒÑÚ ÇáÔÑ íÍÕÏ İí ÚæÇŞÈå',
			'2020-05-11','ÈÚÏ ÇáÚÔÇÁ');
insert into lecture(lec_id,maj_id,title,"date","time") values('1043','104','Õä ÍÑ æÌåß áÇ ÊåÊß ÛáÇáÊå',
			'2020-05-12','ÈÚÏ ÇáÚÔÇÁ');
select default_placeAll('104');				


