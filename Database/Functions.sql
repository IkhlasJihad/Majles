							/* Functions */
create or replace function default_placeAll(varchar) returns void as $s$
update lecture set place = (select place 
							from majles 
							where maj_id = $1)
$s$ language sql; 

--count students 
create or replace function count_maj_students(varchar) returns bigint as $s$
select count(id) 
from joins 
where maj_id = $1;
$s$ language sql;

--count majles lectures
create or replace function count_maj_lectures(varchar) returns bigint as $s$
select count(lec_id) 
from lecture
where maj_id = $1;
$s$ language sql;


--count lecture students
create or replace function count_lec_students(varchar, varchar) returns bigint as $s$
	select count(id)
	from attends natural join lecture
	where lec_id = $1 and maj_id = $2 ;
$s$ language sql;

--attendance percentage
create or replace function attendance_percentage(varchar, varchar) returns numeric as $s$
	select (count_lec_students($1, $2)*1.0 /count_maj_students($2))*100.0;	
	
$s$ language sql;

-- Students attended more than 90% of lectures of the given majles.

CREATE TABLE commitment (id varchar, full_name text, att bigint);
create or replace function getCommitmentRecord(varchar ) returns setof commitment as
$body$
    with attendsHowMany as 
		(select id, maj_id, count (*) as lecCount, 
		 concat_ws(' ',first_name,snd_name,third_name,last_name)  as full_Name 
		 from attends natural join lecture natural join student
		 where maj_id = $1
		 group by maj_id,id,first_name,snd_name,third_name,last_name)
		
		 select  id,full_name,lecCount from attendsHowMany
		where ( lecCount*1.0 / (select count_maj_lectures($1)) ) > 0.9;
$body$ LANGUAGE SQL;

--validateDate
create or replace function is_date(s varchar) returns boolean as $body$
	begin
	  perform s::date;
	  return true;
	exception when others then
	  return false;
	end;
$body$ language plpgsql;

--Isjoined
create or replace function isJoin(s varchar, m varchar) returns boolean as $body$
	begin
		if exists 
			(select * from joins where maj_id = m and id = s )
		then 
			return true;
		end if;
		return false;
	end;
$body$ language plpgsql;

--returns whether majles m contains lecture L
create or replace function contains(L varchar, m varchar) returns boolean as $body$
	begin
		if exists 
			(select * from lecture where maj_id = m and  lec_id = L)
		then 
			return true;
		end if;
		return false;
	end;
$body$ language plpgsql;

-- StdAttendance
create table stdAttends (lec varchar, d date, attendance boolean);
create or replace function std_attends(maj varchar, std varchar) returns setof stdAttends as 
$body$
		with attended as (select lec_id from attends where id = std)
		select distinct L.lec_id, date,
						case when ( L.lec_id in (select lec_id from attended)) then true
						else false
						end
		from lecture L natural left join attended 
		where maj_id = maj
		order by date asc;

$body$ language sql;

--lecAttendanceReport
create table lecReport( id varchar, name text, att int);
create or replace function lecAttendanceReport(maj varchar, lec varchar) returns setof lecReport as 
$body$
	with joined as 
	(select id, concat_ws(' ',first_name,snd_name,third_name,last_name)  as full_Name 
	 from joins natural join student
	 where maj_id = maj
	 group by id,first_name,snd_name,third_name,last_name)
		
	select id, full_Name,
				case when ( id in (select id from attends where lec_id = lec)) then 1
				else 0
				end
	from joined
	order by id asc;
$body$ language sql;
