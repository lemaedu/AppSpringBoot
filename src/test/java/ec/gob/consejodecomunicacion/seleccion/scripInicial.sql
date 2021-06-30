/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  llema
 * Created: 29 jun. 2021
 */

INSERT INTO public.tb_role(role_name,role_status) VALUES ('ROLE_ADMIN',TRUE);
INSERT INTO public.tb_people(peop_id_document,peop_name,peop_status) VALUES ('012345678','Administrador',TRUE);
	
INSERT INTO public.tb_menu(menu_name,menu_father_id,menu_url,menu_order,menu_end_node,menu_status) 
VALUES ('MENU_APP',null,null,0,FALSE,TRUE);

INSERT INTO public.tb_menu(menu_name,menu_father_id,menu_url,menu_order,menu_end_node,menu_status) 
VALUES ('ARCHIVO',(SELECT menu_id FROM public.tb_menu WHERE menu_name='MENU_APP'),null,0,FALSE,TRUE);
INSERT INTO public.tb_menu(menu_name,menu_father_id,menu_url,menu_order,menu_end_node,menu_status) 
VALUES ('SEGURIDAD',(SELECT menu_id FROM public.tb_menu WHERE menu_name='MENU_APP'),null,1,FALSE,TRUE);
INSERT INTO public.tb_menu(menu_name,menu_father_id,menu_url,menu_order,menu_end_node,menu_status) 
VALUES ('REPORTES',(SELECT menu_id FROM public.tb_menu WHERE menu_name='MENU_APP'),null,2,FALSE,TRUE);

INSERT INTO public.tb_menu(menu_name,menu_father_id,menu_url,menu_order,menu_end_node,menu_status) 
VALUES ('Documentos',(SELECT menu_id FROM public.tb_menu WHERE menu_name='ARCHIVO'),null,0,TRUE,TRUE);
INSERT INTO public.tb_menu(menu_name,menu_father_id,menu_url,menu_order,menu_end_node,menu_status) 
VALUES ('Certificado',(SELECT menu_id FROM public.tb_menu WHERE menu_name='ARCHIVO'),null,1,TRUE,TRUE);

INSERT INTO public.tb_menu(menu_name,menu_father_id,menu_url,menu_order,menu_end_node,menu_status)
VALUES ('Usuarios',(SELECT menu_id FROM public.tb_menu WHERE menu_name='SEGURIDAD'),'/usuarios',0,TRUE,TRUE);
INSERT INTO public.tb_menu(menu_name,menu_father_id,menu_url,menu_order,menu_end_node,menu_status)
VALUES ('Roles',(SELECT menu_id FROM public.tb_menu WHERE menu_name='SEGURIDAD'),'/roles',1,TRUE,TRUE);

INSERT INTO public.tb_menu(menu_name,menu_father_id,menu_url,menu_order,menu_end_node,menu_status) 
VALUES ('Usuarios',(SELECT menu_id FROM public.tb_menu WHERE menu_name='REPORTES'),'/usuarios',0,TRUE,TRUE);
INSERT INTO public.tb_menu(menu_name,menu_father_id,menu_url,menu_order,menu_end_node,menu_status) 
VALUES ('Mas',(SELECT menu_id FROM public.tb_menu WHERE menu_name='REPORTES'),null,0,TRUE,TRUE);

INSERT INTO public.tb_user(user_name,peop_id,user_password,user_mail,user_permanent,user_status)
VALUES ('admin',1,'$2a$10$w99BWZKmcPNxdVRKkyWZUOiuTOnN8WatG70RokuJvUGy4suRaHv8m','mail@info.com',TRUE,TRUE);

INSERT INTO public.tb_role_user(role_id,user_id,rous_status) VALUES (1,1,TRUE); 

INSERT INTO public.tb_menu_role(menu_id,role_id,mero_status) VALUES (1,1,TRUE);
INSERT INTO public.tb_menu_role(menu_id,role_id,mero_status) VALUES (2,1,TRUE); 
INSERT INTO public.tb_menu_role(menu_id,role_id,mero_status) VALUES (3,1,TRUE); 
INSERT INTO public.tb_menu_role(menu_id,role_id,mero_status) VALUES (4,1,TRUE); 
INSERT INTO public.tb_menu_role(menu_id,role_id,mero_status) VALUES (5,1,TRUE); 
INSERT INTO public.tb_menu_role(menu_id,role_id,mero_status) VALUES (6,1,TRUE); 
INSERT INTO public.tb_menu_role(menu_id,role_id,mero_status) VALUES (7,1,TRUE); 
INSERT INTO public.tb_menu_role(menu_id,role_id,mero_status) VALUES (8,1,TRUE); 
INSERT INTO public.tb_menu_role(menu_id,role_id,mero_status) VALUES (9,1,TRUE); 
INSERT INTO public.tb_menu_role(menu_id,role_id,mero_status) VALUES (10,1,TRUE); 