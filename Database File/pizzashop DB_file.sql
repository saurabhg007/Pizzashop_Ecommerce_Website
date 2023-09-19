create database pizzashop;
use pizzashop;

delimiter $$
create procedure productbycategory(in catid int)
begin

select * from product p inner join category c where c.catid = p.ct_catid and c.catid = catid;

end$$
delimiter $$;

