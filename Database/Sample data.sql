--Data for testing
-- It's written in Arabic because users of the system may have no English background, 
-- The learning community is Arabic as well.
insert into region values ('265', '������');
insert into region values ('260', '���');
insert into region values ('270', '��� ����');
insert into region values ('255', '���� ���');
insert into region values ('275', '���');
insert into region values ('150', '������');
insert into region values ('115', '��� ����');

insert into category values (default, '�����'); --1
insert into category values (default, '���'); --2
insert into category values (default, '��� � �����');--3
insert into category values (default, '�����');--4
insert into category values (default, '���� ���');--5
insert into category values (default, '���');--6
insert into category values (default, '�����');--7
insert into category values (default, '����');--8
insert into category values (default, '���');--9
insert into category values (default, '����� � ������');--10
insert into category values (default, ' ���� � �����');--11
insert into book values(default,'����� ��� ����' 
						,'����� �����', 
						25.4);--10
insert into book(book_id, title) values(default,'������� ��������');--11
insert into book values(default,'��� ����� �������'
						,'����� ��� ������', 
						7.4); --12
insert into book(book_id, title) values(default,'������� ������');	--13						
insert into book(book_id, title) values (default,'����� ������'); --14
insert into book(book_id, title) values (default,'���� ������ ����'); -- 15
insert into book(book_id, title) values (default,'�������� ��� ��������');--16					
insert into book(book_id, title) values (default, '���� ������ �� ������');--17
insert into book(book_id, title) values(default, '���� ������ ��� �������');--18
insert into book(book_id, title) values (default, '���� ������ �� ������');--19
insert into book(book_id, title) values (default, '������� �� ��� �������');--20
insert into book(book_id, title) values (default, '������� �� ����� ������ ������ �� ����� ������'); --21
insert into book values (default, '������ �� ��� �������'
						,'����� ���� �����', 
						3.0); --22
insert into book values (default, '������ �� ����� �������� ����������  - �� ���� ������ ���� ��� ���� ���� ��������'
						,'����� ��� ������', 
						8.71);		--23			
insert into book values (default, '������ ������� - ���� ��� ����'
  					   ,'����� ��� ������ ', 
						23.0);--24
insert into book(book_id, title) values (default, '����� ������ ��� ���� �������'); --25
insert into book(book_id,title) values(default, '����� ������� �� ��� �������� ����� �� ���� ��������');--26
insert into book(book_id,title) values(default, '������ �� ��������');--27



insert into author values (default, '���� ������ ��� �����');	--1				
insert into author values (default, '��� ����� ������');		--2			
insert into author values (default, '��� �����');--3
insert into author values (default, '���� ��� ������');--4					
insert into author values (default, '���� ���� ���');--5
insert into author values (default, '���� ������');--6
insert into author values (default, '��� ����� ��������');--7
insert into author values (default, '����������');--8
insert into author values (default, '��� ������');--9
insert into author values (default, '���� ������ ������');--10
insert into author values (default, '��� ������ �����');--11
insert into author values (default, '��� ����� �� ���� ��������'); --12
insert into author values (default, '���� ���'); --13
insert into author values(default, '���� ������'); --14
insert into author values(default, '��� �����');--15
insert into author values (default, '���� �� ���');	--16	
			
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

insert into sheikh(sh_id,"name",gender, email, region_code) values('22','��� ��������','M','Domar@qraat.com','265' ) ;
insert into sheikh(sh_id,"name",gender,region_code) values('24','���� �����','M','260' ) ;
insert into sheikh(sh_id,"name",gender,region_code) values('245','����� ������','M','260' ) ;
insert into sheikh(sh_id,"name",gender) values('352','���� ��� ������','M') ;
insert into sheikh(sh_id,"name",gender) values('321','���� ������','M') ;

insert into majles (maj_id,sh_id,place,name,subject) values('101', '22','Zoom', 
		'������ ������ ����� ����� ��� �������� �����', 
		'���� ��������');

insert into majles(maj_id,sh_id,place,name,subject) values('102', '245','ar.islamway.net', 
		'��� ���� ������ �� ���� �������', 
		'����� ������ ������ ������');	

insert into majles(maj_id,sh_id,place,name,subject) values('103', '321','Zoom', 
		'���� ������ ����', 
		'����� �������');
insert into majles(maj_id,sh_id,place,name,subject) values('104', '321','Zoom', 
		'��� ����� ����� ����� ���� ����� ������', 
		'����� ������');	
insert into elucidates values('103','15');
insert into elucidates values('102','25');
insert into elucidates values('102','18');
	
insert into student values(default, '����',
	'����',
	'����',
	'����', 'M', '265');
insert into student values(default, '����',
	'����',
	'���',
	'���', 'M', '265');
insert into student values(default, '����',
	'�����',
	'�����',
	'������', 'M', '265');
insert into student values(default, '�����',
	'��',
	'�����',
	'������', 'M', '265');
insert into student values(default, '����',
	'���',
	'����',
	'������', 'M', '265');
insert into student values(default, '���',
	'���',
	'����',
	'������', 'M', '265');
insert into student values(default, '����',
	'����',
	'����',
	'��� �����', 'M', '265');
insert into student values(default, '����',
	'����',
	'����',
	'��� �����', 'M', '265');

insert into student  values(default, '����',
	'����',
	'����'
	,'���', 'M', '265');
insert into student values(default, '����',
	'����',
	'����'
	,'���', 'M', '265');
insert into student values(default, '����',
	'���',
	'����'
	,'���', 'M', '265');
insert into student values(default, '���',
	'����',
	'����',
	'����', 'M', '265');
insert into student values(default, '����',
	'����',
	'�����',
	'����', 'M', '265');
insert into student values(default, '����',
	'���',
	'���',
	'���', 'M', '265');
insert into student values(default, '����',
	'����',
	'�����',
	'�������', 'M', '260');
insert into student values(default, '����',
	'�����',
	'����',
	'���', 'M', '260');
insert into student values(default, '����',
	'����',
	'�����',
	'�����', 'M', '260');
insert into student values(default, '�����',
	'����',
	'�����',
	'�����', 'M', '260');
insert into student values(default, '���',
	'����',
	'�����',
	'�����', 'F', '260');
insert into student values(default, '���',
	'���',
	'����',
	'������', 'F', '260');
insert into student values(default, '����',
	'����',
	'����',
	'�������', 'F', '260');
insert into student values(default, '���',
	'����',
	'����',
	'�������', 'F', '260');
insert into student values(default, '����',
	'�����',
	'����',
	'�����', 'F', '265'); --23
insert into student values(default, '���',
	'����',
	'����',
	'�����', 'F', '265'); --24
insert into student values(default, '�����',
	'����',
	'����',
	'�����', 'F', '265'); --25
insert into student values(default, '���',
	'���',
	'����',
	'����', 'F', '265');--26

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



/*insert into volunteer values('ahmad01', '���� ����', 
							 '265');
insert into responsible_for values('ahmad01', '101');*/							
insert into volunteer values('emyw', '�����', 
							 '265');
insert into responsible_for values('emyw', '101');
insert into responsible_for values('emyw', '104');
insert into volunteer values('mahmoud86', '�����', 
							 '260');							
insert into responsible_for values('mahmoud86', '102');							

insert into lecture(lec_id,maj_id,title,"date","time") values('1','102','�����',
			'2020-03-15','��� �����'); -- the default place is that of the majles.
insert into lecture(lec_id,maj_id,title,"date","time") values('2','102','����� �� ���� ������ �������',
			'2020-03-17','��� �����');
insert into lecture(lec_id,maj_id,title,"date","time") values('3','102','������ ����� ����� ���� �� �����',
			'2020-03-22','��� �����');		
insert into lecture(lec_id,maj_id,title,"date","time") values('4','102','����� �������',
			'2020-03-24','��� �����');							
insert into lecture(lec_id,maj_id,title,"date","time") values('5','102','�� ��� ��� ��� ������',
			'2020-03-29','��� �����');					
insert into lecture(lec_id,maj_id,title,"date","time") values('6','102','��� ������ �� ������',
			'2020-03-31','��� �����');							
insert into lecture(lec_id,maj_id,title,"date","time") values('7','102','����� ���������',
			'2020-04-05','��� �����');							
insert into lecture(lec_id,maj_id,title,"date","time") values('8','102','���� �����',
			'2020-04-07','��� �����');							
insert into lecture(lec_id,maj_id,title,"date","time") values('9','102','������ �� �����',
			'2020-04-12','��� �����');							
insert into lecture(lec_id,maj_id,title,"date","time") values('10','102','����� ���� �����',
			'2020-04-14','��� �����');							
insert into lecture(lec_id,maj_id,title,"date","time") values('11','102','������ ���� �� ���� �����',
			'2020-04-19','��� �����');							
insert into lecture(lec_id,maj_id,title,"date","time") values('12','102','�� ��� ���� ���� ��� ���� �����',
			'2020-04-21','��� �����');	
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

insert into lecture(lec_id,maj_id,title,"date","time") values('1041','104','����� ����� �� ����� �����',
			'2020-05-10','��� ������');	
insert into lecture(lec_id,maj_id,title,"date","time") values('1042','104','�� ���� ���� ���� �� ������',
			'2020-05-11','��� ������');
insert into lecture(lec_id,maj_id,title,"date","time") values('1043','104','�� �� ���� �� ���� ������',
			'2020-05-12','��� ������');
select default_placeAll('104');				


