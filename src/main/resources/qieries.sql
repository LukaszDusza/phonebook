SELECT *
FROM contact
WHERE fk_category = (select id from category where title = "friends");

//wyszukiwanie contact po tag title.
select contact.id,
       contact.name,
       contact.surname,
       contact.number,
       contact.fk_ranking,
       contact.fk_address,
       contact.fk_category,
       tag.id,
       tag.title
from contact
       join contact_tag on contact.id = contact_tag.contact_id
       join tag on contact_tag.tag_id = tag.id
where tag.id = (select id from tag where title = '#partytime')


//wyszukiwanie contact po tag title.
select contact.id,
       contact.name,
       contact.number,
       contact.surname,
       contact.fk_address,
       contact.fk_category,
       contact.fk_ranking
from contact
       join contact_tag on contact.id = contact_tag.contact_id
       join tag on contact_tag.tag_id = tag.id
where tag.id = (select id from tag where title = '#poland')
