--Queries

--Display the list of students who missed 3 consecutive lectures 
/*  
 * Using function std_attends(maj varchar, std varchar) i've implemented 
 * that returns stdAttends table (lec varchar, d date, attendance boolean)
*/
-- for example : maj 102
select distinct id, concat_ws(' ',first_name,snd_name,third_name,last_name) as full_Name 
from joins natural join student j 
where maj_id = '102' and exists (select * 
								 from std_attends('102',j.id) as r1
								 where not r1.attendance 
								 	   and exists (select * 
												   from std_attends('102',j.id) as r2
												   where not r2.attendance 
												   		and cast(r2.lec as int) = cast(r1.lec as int) + 1 
												   		and exists (select * 
																    from std_attends('102',j.id) as r3
																	where not r3.attendance 
																		and cast(r3.lec as int) = cast(r2.lec as int) + 1 )
													)
								)
group by id, first_name,snd_name, third_name,last_name;

--What are the top 10 most attended lectures of all time?
select lec_id, count_lec_students(lec_id,maj_id) as attendedBy
from attends natural join lecture
group by maj_id, lec_id
order by attendedBy desc
limit 10;

--What are the lectures that had more students missing that lecture than actually attending it? 
--(if a student joined after that lecture, we should not count him as absent)
with attendanceCount as (select lec_id, title, coalesce(count(id),0) as attendedBy
 			  from lecture natural left join attends 
 			  where maj_id = '102'
 			  group by lec_id, title)
select *
from attendanceCount
where attendedBy < (select count_maj_students('102')  - attendedBy);

--ordered by their ‘commitment’ from the most committed to the least. (commitment )		
select id, count (*) as lecCount, 
concat_ws(' ',first_name,snd_name,third_name,last_name)  as full_Name 
from attends natural join lecture natural join student
where maj_id = '102'
group by id,first_name,snd_name,third_name,last_name
order by lecCount desc;

--Display the list of students who attended more than 90% of lectures.
--using the function i'v implemented
select * from getCommitmentRecord('102');
		

-- List of students who attends a (given) majles late.
--example maj_id = '102'
select id, join_in, concat_ws(' ',first_name,snd_name,third_name,last_name) as full_Name
from joins natural join student 
where maj_id = '102' and join_in > (select min(date)
					   				from lecture where maj_id = joins.maj_id)
group by id,join_in,first_name,snd_name,third_name,last_name;
																	
