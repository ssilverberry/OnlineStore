BEGIN
INSERT INTO USERS VALUES(DEFAULT, 'superAdmin', null, 'superAdmin@gmail.com', null, 'superAdmin', null, 1);
INSERT INTO USERS VALUES(DEFAULT, 'test1', 'test1', 'test1@gmail.com', '(+38)-099-99-99', 'test1', null, 0);
INSERT INTO USERS VALUES(DEFAULT, 'test2', 'test2', 'test2@gmail.com', '(+38)-099-98-98', 'test2', null, 0);
INSERT INTO USERS VALUES(DEFAULT, 'test3', 'test3', 'test3@gmail.com', '(+38)-099-97-97', 'test3', null, 0);

INSERT INTO PRODUCTS VALUES(DEFAULT, null, 'Laptops, PC', 1);
INSERT INTO PRODUCTS VALUES(DEFAULT, null, 'Smartphones', 1);
INSERT INTO PRODUCTS VALUES(DEFAULT, null, 'TV, electronics', 1);

INSERT INTO PRODUCTS VALUES(DEFAULT, 
                            (SELECT product_id FROM PRODUCTS WHERE product_name = 'Laptops, PC'), 
                            'Apple MacBook Air Retina 13"', 0);
INSERT INTO PRODUCTS VALUES(DEFAULT, 
                            (SELECT product_id FROM PRODUCTS WHERE product_name = 'Laptops, PC'), 
                            'Apple iMac 21.5" Retina 4K', 0);
INSERT INTO PRODUCTS VALUES(DEFAULT, 
                            (SELECT product_id FROM PRODUCTS WHERE product_name = 'Laptops, PC'), 
                            'Apple MacBook Pro Retina 15"', 0);
                            
INSERT INTO PRODUCTS VALUES(DEFAULT, 
                            (SELECT product_id FROM PRODUCTS WHERE product_name = 'Smartphones'), 
                            'Apple iPhone 8 256Gb RED', 0);
INSERT INTO PRODUCTS VALUES(DEFAULT, 
                            (SELECT product_id FROM PRODUCTS WHERE product_name = 'Smartphones'), 
                            'Apple iPhone 10 256Gb Space Gray', 0);
INSERT INTO PRODUCTS VALUES(DEFAULT, 
                            (SELECT product_id FROM PRODUCTS WHERE product_name = 'Smartphones'), 
                            'Apple iPhone 7 128Gb Space Gray', 0);
                            
INSERT INTO PRODUCTS VALUES(DEFAULT, 
                            (SELECT product_id FROM PRODUCTS WHERE product_name = 'TV, electronics'), 
                            'TV LG 43UJ630V', 0);
                            
INSERT INTO PRODUCTS_ATTRIBUTES VALUES(DEFAULT, 
                            (SELECT product_id FROM PRODUCTS WHERE product_name = 'Laptops, PC'), 
                            'Processor');
INSERT INTO PRODUCTS_ATTRIBUTES VALUES(DEFAULT, 
                            (SELECT product_id FROM PRODUCTS WHERE product_name = 'Laptops, PC'), 
                            'Display');
INSERT INTO PRODUCTS_ATTRIBUTES VALUES(DEFAULT, 
                            (SELECT product_id FROM PRODUCTS WHERE product_name = 'Laptops, PC'), 
                            'Memory');
INSERT INTO PRODUCTS_ATTRIBUTES VALUES(DEFAULT, 
                            (SELECT product_id FROM PRODUCTS WHERE product_name = 'Laptops, PC'), 
                            'Storage');
INSERT INTO PRODUCTS_ATTRIBUTES VALUES(DEFAULT, 
                            (SELECT product_id FROM PRODUCTS WHERE product_name = 'Laptops, PC'), 
                            'Height');
INSERT INTO PRODUCTS_ATTRIBUTES VALUES(DEFAULT, 
                            (SELECT product_id FROM PRODUCTS WHERE product_name = 'Laptops, PC'), 
                            'Weight');
INSERT INTO PRODUCTS_ATTRIBUTES VALUES(DEFAULT, 
                            (SELECT product_id FROM PRODUCTS WHERE product_name = 'Laptops, PC'), 
                            'Price');
                            
INSERT INTO PRODUCTS_ATTRIBUTES VALUES(DEFAULT, 
                            (SELECT product_id FROM PRODUCTS WHERE product_name = 'Smartphones'), 
                            'Processor');
INSERT INTO PRODUCTS_ATTRIBUTES VALUES(DEFAULT, 
                            (SELECT product_id FROM PRODUCTS WHERE product_name = 'Smartphones'), 
                            'Display');
INSERT INTO PRODUCTS_ATTRIBUTES VALUES(DEFAULT, 
                            (SELECT product_id FROM PRODUCTS WHERE product_name = 'Smartphones'), 
                            'Memory');
INSERT INTO PRODUCTS_ATTRIBUTES VALUES(DEFAULT, 
                            (SELECT product_id FROM PRODUCTS WHERE product_name = 'Smartphones'), 
                            'Storage');
INSERT INTO PRODUCTS_ATTRIBUTES VALUES(DEFAULT, 
                            (SELECT product_id FROM PRODUCTS WHERE product_name = 'Smartphones'), 
                            'Height');
INSERT INTO PRODUCTS_ATTRIBUTES VALUES(DEFAULT, 
                            (SELECT product_id FROM PRODUCTS WHERE product_name = 'Smartphones'), 
                            'Weight');
INSERT INTO PRODUCTS_ATTRIBUTES VALUES(DEFAULT, 
                            (SELECT product_id FROM PRODUCTS WHERE product_name = 'Smartphones'), 
                            'Price');
                            
INSERT INTO PRODUCTS_ATTRIBUTES VALUES(DEFAULT, 
                            (SELECT product_id FROM PRODUCTS WHERE product_name = 'TV, electronics'), 
                            'Resolution');
INSERT INTO PRODUCTS_ATTRIBUTES VALUES(DEFAULT, 
                            (SELECT product_id FROM PRODUCTS WHERE product_name = 'TV, electronics'), 
                            'Display');
INSERT INTO PRODUCTS_ATTRIBUTES VALUES(DEFAULT, 
                            (SELECT product_id FROM PRODUCTS WHERE product_name = 'TV, electronics'), 
                            'Frequence');
INSERT INTO PRODUCTS_ATTRIBUTES VALUES(DEFAULT, 
                            (SELECT product_id FROM PRODUCTS WHERE product_name = 'TV, electronics'), 
                            'Audio');
INSERT INTO PRODUCTS_ATTRIBUTES VALUES(DEFAULT, 
                            (SELECT product_id FROM PRODUCTS WHERE product_name = 'TV, electronics'), 
                            'Height');
INSERT INTO PRODUCTS_ATTRIBUTES VALUES(DEFAULT, 
                            (SELECT product_id FROM PRODUCTS WHERE product_name = 'TV, electronics'), 
                            'Weight');
INSERT INTO PRODUCTS_ATTRIBUTES VALUES(DEFAULT, 
                            (SELECT product_id FROM PRODUCTS WHERE product_name = 'TV, electronics'), 
                            'Price');
                            
INSERT INTO PRODUCTS_PARAMETERS VALUES ((SELECT product_id FROM PRODUCTS WHERE product_name = 'Apple MacBook Air Retina 13"'),
                                        (SELECT attribute_id FROM PRODUCTS_ATTRIBUTES WHERE attribute_name='Processor' 
                                         AND product_id=(SELECT product_id FROM PRODUCTS prod WHERE product_name='Laptops, PC')),
                                        'Dual-core Intel Core i5 (1.8 - 2.9 GHz)');
INSERT INTO PRODUCTS_PARAMETERS VALUES ((SELECT product_id FROM PRODUCTS WHERE product_name = 'Apple MacBook Air Retina 13"'),
                                        (SELECT attribute_id FROM PRODUCTS_ATTRIBUTES WHERE attribute_name='Display' 
                                         AND product_id=(SELECT product_id FROM PRODUCTS prod WHERE product_name='Laptops, PC')),
                                        '13.3" (1440x900) WXGA+');                                        
INSERT INTO PRODUCTS_PARAMETERS VALUES ((SELECT product_id FROM PRODUCTS WHERE product_name = 'Apple MacBook Air Retina 13"'),
                                        (SELECT attribute_id FROM PRODUCTS_ATTRIBUTES WHERE attribute_name='Memory' 
                                         AND product_id=(SELECT product_id FROM PRODUCTS prod WHERE product_name='Laptops, PC')),
                                        '8GB DDR4'); 
INSERT INTO PRODUCTS_PARAMETERS VALUES ((SELECT product_id FROM PRODUCTS WHERE product_name = 'Apple MacBook Air Retina 13"'),
                                        (SELECT attribute_id FROM PRODUCTS_ATTRIBUTES WHERE attribute_name='Storage' 
                                         AND product_id=(SELECT product_id FROM PRODUCTS prod WHERE product_name='Laptops, PC')),
                                        '256 GB SSD');
INSERT INTO PRODUCTS_PARAMETERS VALUES ((SELECT product_id FROM PRODUCTS WHERE product_name = 'Apple MacBook Air Retina 13"'),
                                        (SELECT attribute_id FROM PRODUCTS_ATTRIBUTES WHERE attribute_name='Weight' 
                                         AND product_id=(SELECT product_id FROM PRODUCTS prod WHERE product_name='Laptops, PC')),
                                        '325 mm');
INSERT INTO PRODUCTS_PARAMETERS VALUES ((SELECT product_id FROM PRODUCTS WHERE product_name = 'Apple MacBook Air Retina 13"'),
                                        (SELECT attribute_id FROM PRODUCTS_ATTRIBUTES WHERE attribute_name='Height' 
                                         AND product_id=(SELECT product_id FROM PRODUCTS prod WHERE product_name='Laptops, PC')),
                                        '3~17 mm');
INSERT INTO PRODUCTS_PARAMETERS VALUES ((SELECT product_id FROM PRODUCTS WHERE product_name = 'Apple MacBook Air Retina 13"'),
                                        (SELECT attribute_id FROM PRODUCTS_ATTRIBUTES WHERE attribute_name='Price' 
                                         AND product_id=(SELECT product_id FROM PRODUCTS prod WHERE product_name='Laptops, PC')),
                                        '1000');                                        

INSERT INTO PRODUCTS_PARAMETERS VALUES ((SELECT product_id FROM PRODUCTS WHERE product_name = 'Apple MacBook Pro Retina 15"'),
                                        (SELECT attribute_id FROM PRODUCTS_ATTRIBUTES WHERE attribute_name='Processor' 
                                         AND product_id=(SELECT product_id FROM PRODUCTS prod WHERE product_name='Laptops, PC')),
                                        'Quad-Core Intel Core i7 (2.9-3.9 GHz)');
INSERT INTO PRODUCTS_PARAMETERS VALUES ((SELECT product_id FROM PRODUCTS WHERE product_name = 'Apple MacBook Pro Retina 15"'),
                                        (SELECT attribute_id FROM PRODUCTS_ATTRIBUTES WHERE attribute_name='Display' 
                                         AND product_id=(SELECT product_id FROM PRODUCTS prod WHERE product_name='Laptops, PC')),
                                        '15.4" (2880x1800)');                                        
INSERT INTO PRODUCTS_PARAMETERS VALUES ((SELECT product_id FROM PRODUCTS WHERE product_name = 'Apple MacBook Pro Retina 15"'),
                                        (SELECT attribute_id FROM PRODUCTS_ATTRIBUTES WHERE attribute_name='Memory' 
                                         AND product_id=(SELECT product_id FROM PRODUCTS prod WHERE product_name='Laptops, PC')),
                                        '16GB DDR4'); 
INSERT INTO PRODUCTS_PARAMETERS VALUES ((SELECT product_id FROM PRODUCTS WHERE product_name = 'Apple MacBook Pro Retina 15"'),
                                        (SELECT attribute_id FROM PRODUCTS_ATTRIBUTES WHERE attribute_name='Storage' 
                                         AND product_id=(SELECT product_id FROM PRODUCTS prod WHERE product_name='Laptops, PC')),
                                        '512 GB SSD');
INSERT INTO PRODUCTS_PARAMETERS VALUES ((SELECT product_id FROM PRODUCTS WHERE product_name = 'Apple MacBook Pro Retina 15"'),
                                        (SELECT attribute_id FROM PRODUCTS_ATTRIBUTES WHERE attribute_name='Weight' 
                                         AND product_id=(SELECT product_id FROM PRODUCTS prod WHERE product_name='Laptops, PC')),
                                        '349.3 mm');
INSERT INTO PRODUCTS_PARAMETERS VALUES ((SELECT product_id FROM PRODUCTS WHERE product_name = 'Apple MacBook Pro Retina 15"'),
                                        (SELECT attribute_id FROM PRODUCTS_ATTRIBUTES WHERE attribute_name='Height' 
                                         AND product_id=(SELECT product_id FROM PRODUCTS prod WHERE product_name='Laptops, PC')),
                                        '15.5 mm');
INSERT INTO PRODUCTS_PARAMETERS VALUES ((SELECT product_id FROM PRODUCTS WHERE product_name = 'Apple MacBook Pro Retina 15"'),
                                        (SELECT attribute_id FROM PRODUCTS_ATTRIBUTES WHERE attribute_name='Price' 
                                         AND product_id=(SELECT product_id FROM PRODUCTS prod WHERE product_name='Laptops, PC')),
                                        '1500');                                         
                                        
INSERT INTO PRODUCTS_PARAMETERS VALUES ((SELECT product_id FROM PRODUCTS WHERE product_name = 'Apple iMac 21.5" Retina 4K'),
                                        (SELECT attribute_id FROM PRODUCTS_ATTRIBUTES WHERE attribute_name='Processor' 
                                         AND product_id=(SELECT product_id FROM PRODUCTS prod WHERE product_name='Laptops, PC')),
                                        'Quad-Core Intel Core i5 (3.0 - 3.5 GHz)');
INSERT INTO PRODUCTS_PARAMETERS VALUES ((SELECT product_id FROM PRODUCTS WHERE product_name = 'Apple iMac 21.5" Retina 4K'),
                                        (SELECT attribute_id FROM PRODUCTS_ATTRIBUTES WHERE attribute_name='Display' 
                                         AND product_id=(SELECT product_id FROM PRODUCTS prod WHERE product_name='Laptops, PC')),
                                        '21.5" IPS Retina (4096x2304) 4K LED');                                        
INSERT INTO PRODUCTS_PARAMETERS VALUES ((SELECT product_id FROM PRODUCTS WHERE product_name = 'Apple iMac 21.5" Retina 4K'),
                                        (SELECT attribute_id FROM PRODUCTS_ATTRIBUTES WHERE attribute_name='Memory' 
                                         AND product_id=(SELECT product_id FROM PRODUCTS prod WHERE product_name='Laptops, PC')),
                                        '8GB DDR4'); 
INSERT INTO PRODUCTS_PARAMETERS VALUES ((SELECT product_id FROM PRODUCTS WHERE product_name = 'Apple iMac 21.5" Retina 4K'),
                                        (SELECT attribute_id FROM PRODUCTS_ATTRIBUTES WHERE attribute_name='Storage' 
                                         AND product_id=(SELECT product_id FROM PRODUCTS prod WHERE product_name='Laptops, PC')),
                                        '1 TB HDD');
INSERT INTO PRODUCTS_PARAMETERS VALUES ((SELECT product_id FROM PRODUCTS WHERE product_name = 'Apple iMac 21.5" Retina 4K'),
                                        (SELECT attribute_id FROM PRODUCTS_ATTRIBUTES WHERE attribute_name='Weight' 
                                         AND product_id=(SELECT product_id FROM PRODUCTS prod WHERE product_name='Laptops, PC')),
                                        '528 mm');
INSERT INTO PRODUCTS_PARAMETERS VALUES ((SELECT product_id FROM PRODUCTS WHERE product_name = 'Apple iMac 21.5" Retina 4K'),
                                        (SELECT attribute_id FROM PRODUCTS_ATTRIBUTES WHERE attribute_name='Height' 
                                         AND product_id=(SELECT product_id FROM PRODUCTS prod WHERE product_name='Laptops, PC')),
                                        '450 mm');  
INSERT INTO PRODUCTS_PARAMETERS VALUES ((SELECT product_id FROM PRODUCTS WHERE product_name = 'Apple iMac 21.5" Retina 4K'),
                                        (SELECT attribute_id FROM PRODUCTS_ATTRIBUTES WHERE attribute_name='Price' 
                                         AND product_id=(SELECT product_id FROM PRODUCTS prod WHERE product_name='Laptops, PC')),
                                        '2000');                                        
                              
INSERT INTO PRODUCTS_PARAMETERS VALUES ((SELECT product_id FROM PRODUCTS WHERE product_name = 'Apple iPhone 8 256Gb RED'),
                                        (SELECT attribute_id FROM PRODUCTS_ATTRIBUTES WHERE attribute_name='Processor' 
                                         AND product_id=(SELECT product_id FROM PRODUCTS prod WHERE product_name='Smartphones')),
                                        '2+2 A11 Bionic');
INSERT INTO PRODUCTS_PARAMETERS VALUES ((SELECT product_id FROM PRODUCTS WHERE product_name = 'Apple iPhone 8 256Gb RED'),
                                        (SELECT attribute_id FROM PRODUCTS_ATTRIBUTES WHERE attribute_name='Display' 
                                         AND product_id=(SELECT product_id FROM PRODUCTS prod WHERE product_name='Smartphones')),
                                        '4.7 (1334x750) IPS');                                        
INSERT INTO PRODUCTS_PARAMETERS VALUES ((SELECT product_id FROM PRODUCTS WHERE product_name = 'Apple iPhone 8 256Gb RED'),
                                        (SELECT attribute_id FROM PRODUCTS_ATTRIBUTES WHERE attribute_name='Memory' 
                                         AND product_id=(SELECT product_id FROM PRODUCTS prod WHERE product_name='Smartphones')),
                                        '2GB'); 
INSERT INTO PRODUCTS_PARAMETERS VALUES ((SELECT product_id FROM PRODUCTS WHERE product_name = 'Apple iPhone 8 256Gb RED'),
                                        (SELECT attribute_id FROM PRODUCTS_ATTRIBUTES WHERE attribute_name='Storage' 
                                         AND product_id=(SELECT product_id FROM PRODUCTS prod WHERE product_name='Smartphones')),
                                        '256GB');
INSERT INTO PRODUCTS_PARAMETERS VALUES ((SELECT product_id FROM PRODUCTS WHERE product_name = 'Apple iPhone 8 256Gb RED'),
                                        (SELECT attribute_id FROM PRODUCTS_ATTRIBUTES WHERE attribute_name='Weight' 
                                         AND product_id=(SELECT product_id FROM PRODUCTS prod WHERE product_name='Smartphones')),
                                        '67.3 mm');
INSERT INTO PRODUCTS_PARAMETERS VALUES ((SELECT product_id FROM PRODUCTS WHERE product_name = 'Apple iPhone 8 256Gb RED'),
                                        (SELECT attribute_id FROM PRODUCTS_ATTRIBUTES WHERE attribute_name='Height' 
                                         AND product_id=(SELECT product_id FROM PRODUCTS prod WHERE product_name='Smartphones')),
                                        '7.3 mm');  
INSERT INTO PRODUCTS_PARAMETERS VALUES ((SELECT product_id FROM PRODUCTS WHERE product_name = 'Apple iPhone 8 256Gb RED'),
                                        (SELECT attribute_id FROM PRODUCTS_ATTRIBUTES WHERE attribute_name='Price' 
                                         AND product_id=(SELECT product_id FROM PRODUCTS prod WHERE product_name='Smartphones')),
                                        '700');  

INSERT INTO PRODUCTS_PARAMETERS VALUES ((SELECT product_id FROM PRODUCTS WHERE product_name = 'Apple iPhone 10 256Gb Space Gray'),
                                        (SELECT attribute_id FROM PRODUCTS_ATTRIBUTES WHERE attribute_name='Processor' 
                                         AND product_id=(SELECT product_id FROM PRODUCTS prod WHERE product_name='Smartphones')),
                                        '4+2 A11 Bionic');
INSERT INTO PRODUCTS_PARAMETERS VALUES ((SELECT product_id FROM PRODUCTS WHERE product_name = 'Apple iPhone 10 256Gb Space Gray'),
                                        (SELECT attribute_id FROM PRODUCTS_ATTRIBUTES WHERE attribute_name='Display' 
                                         AND product_id=(SELECT product_id FROM PRODUCTS prod WHERE product_name='Smartphones')),
                                        '5.8 OLED (Super Retina HD)');                                        
INSERT INTO PRODUCTS_PARAMETERS VALUES ((SELECT product_id FROM PRODUCTS WHERE product_name = 'Apple iPhone 10 256Gb Space Gray'),
                                        (SELECT attribute_id FROM PRODUCTS_ATTRIBUTES WHERE attribute_name='Memory' 
                                         AND product_id=(SELECT product_id FROM PRODUCTS prod WHERE product_name='Smartphones')),
                                        '3GB'); 
INSERT INTO PRODUCTS_PARAMETERS VALUES ((SELECT product_id FROM PRODUCTS WHERE product_name = 'Apple iPhone 10 256Gb Space Gray'),
                                        (SELECT attribute_id FROM PRODUCTS_ATTRIBUTES WHERE attribute_name='Storage' 
                                         AND product_id=(SELECT product_id FROM PRODUCTS prod WHERE product_name='Smartphones')),
                                        '256GB');
INSERT INTO PRODUCTS_PARAMETERS VALUES ((SELECT product_id FROM PRODUCTS WHERE product_name = 'Apple iPhone 10 256Gb Space Gray'),
                                        (SELECT attribute_id FROM PRODUCTS_ATTRIBUTES WHERE attribute_name='Weight' 
                                         AND product_id=(SELECT product_id FROM PRODUCTS prod WHERE product_name='Smartphones')),
                                        '70.9 mm');
INSERT INTO PRODUCTS_PARAMETERS VALUES ((SELECT product_id FROM PRODUCTS WHERE product_name = 'Apple iPhone 10 256Gb Space Gray'),
                                        (SELECT attribute_id FROM PRODUCTS_ATTRIBUTES WHERE attribute_name='Height' 
                                         AND product_id=(SELECT product_id FROM PRODUCTS prod WHERE product_name='Smartphones')),
                                        '7.7 mm'); 
INSERT INTO PRODUCTS_PARAMETERS VALUES ((SELECT product_id FROM PRODUCTS WHERE product_name = 'Apple iPhone 10 256Gb Space Gray'),
                                        (SELECT attribute_id FROM PRODUCTS_ATTRIBUTES WHERE attribute_name='Price' 
                                         AND product_id=(SELECT product_id FROM PRODUCTS prod WHERE product_name='Smartphones')),
                                        '600');                                         

INSERT INTO PRODUCTS_PARAMETERS VALUES ((SELECT product_id FROM PRODUCTS WHERE product_name = 'Apple iPhone 7 128Gb Space Gray'),
                                        (SELECT attribute_id FROM PRODUCTS_ATTRIBUTES WHERE attribute_name='Processor' 
                                         AND product_id=(SELECT product_id FROM PRODUCTS prod WHERE product_name='Smartphones')),
                                        'Apple A10 Fusion');
INSERT INTO PRODUCTS_PARAMETERS VALUES ((SELECT product_id FROM PRODUCTS WHERE product_name = 'Apple iPhone 7 128Gb Space Gray'),
                                        (SELECT attribute_id FROM PRODUCTS_ATTRIBUTES WHERE attribute_name='Display' 
                                         AND product_id=(SELECT product_id FROM PRODUCTS prod WHERE product_name='Smartphones')),
                                        '4.7", IPS, (1334x750)');                                        
INSERT INTO PRODUCTS_PARAMETERS VALUES ((SELECT product_id FROM PRODUCTS WHERE product_name = 'Apple iPhone 7 128Gb Space Gray'),
                                        (SELECT attribute_id FROM PRODUCTS_ATTRIBUTES WHERE attribute_name='Memory' 
                                         AND product_id=(SELECT product_id FROM PRODUCTS prod WHERE product_name='Smartphones')),
                                        '2GB'); 
INSERT INTO PRODUCTS_PARAMETERS VALUES ((SELECT product_id FROM PRODUCTS WHERE product_name = 'Apple iPhone 7 128Gb Space Gray'),
                                        (SELECT attribute_id FROM PRODUCTS_ATTRIBUTES WHERE attribute_name='Storage' 
                                         AND product_id=(SELECT product_id FROM PRODUCTS prod WHERE product_name='Smartphones')),
                                        '128GB');
INSERT INTO PRODUCTS_PARAMETERS VALUES ((SELECT product_id FROM PRODUCTS WHERE product_name = 'Apple iPhone 7 128Gb Space Gray'),
                                        (SELECT attribute_id FROM PRODUCTS_ATTRIBUTES WHERE attribute_name='Weight' 
                                         AND product_id=(SELECT product_id FROM PRODUCTS prod WHERE product_name='Smartphones')),
                                        '67.1 mm');
INSERT INTO PRODUCTS_PARAMETERS VALUES ((SELECT product_id FROM PRODUCTS WHERE product_name = 'Apple iPhone 7 128Gb Space Gray'),
                                        (SELECT attribute_id FROM PRODUCTS_ATTRIBUTES WHERE attribute_name='Height' 
                                         AND product_id=(SELECT product_id FROM PRODUCTS prod WHERE product_name='Smartphones')),
                                        '7.1 mm'); 
INSERT INTO PRODUCTS_PARAMETERS VALUES ((SELECT product_id FROM PRODUCTS WHERE product_name = 'Apple iPhone 7 128Gb Space Gray'),
                                        (SELECT attribute_id FROM PRODUCTS_ATTRIBUTES WHERE attribute_name='Price' 
                                         AND product_id=(SELECT product_id FROM PRODUCTS prod WHERE product_name='Smartphones')),
                                        '600');                                         

INSERT INTO PRODUCTS_PARAMETERS VALUES ((SELECT product_id FROM PRODUCTS WHERE product_name = 'TV LG 43UJ630V'),
                                        (SELECT attribute_id FROM PRODUCTS_ATTRIBUTES WHERE attribute_name='Resolution' 
                                         AND product_id=(SELECT product_id FROM PRODUCTS prod WHERE product_name='TV, electronics')),
                                        '3840x2160');
INSERT INTO PRODUCTS_PARAMETERS VALUES ((SELECT product_id FROM PRODUCTS WHERE product_name = 'TV LG 43UJ630V'),
                                        (SELECT attribute_id FROM PRODUCTS_ATTRIBUTES WHERE attribute_name='Display' 
                                         AND product_id=(SELECT product_id FROM PRODUCTS prod WHERE product_name='TV, electronics')),
                                        '43", Color Master Engine');                                        
INSERT INTO PRODUCTS_PARAMETERS VALUES ((SELECT product_id FROM PRODUCTS WHERE product_name = 'TV LG 43UJ630V'),
                                        (SELECT attribute_id FROM PRODUCTS_ATTRIBUTES WHERE attribute_name='Frequence' 
                                         AND product_id=(SELECT product_id FROM PRODUCTS prod WHERE product_name='TV, electronics')),
                                        'PMI 1600 G'); 
INSERT INTO PRODUCTS_PARAMETERS VALUES ((SELECT product_id FROM PRODUCTS WHERE product_name = 'TV LG 43UJ630V'),
                                        (SELECT attribute_id FROM PRODUCTS_ATTRIBUTES WHERE attribute_name='Audio' 
                                         AND product_id=(SELECT product_id FROM PRODUCTS prod WHERE product_name='TV, electronics')),
                                        'Ultra Surround');
INSERT INTO PRODUCTS_PARAMETERS VALUES ((SELECT product_id FROM PRODUCTS WHERE product_name = 'TV LG 43UJ630V'),
                                        (SELECT attribute_id FROM PRODUCTS_ATTRIBUTES WHERE attribute_name='Weight' 
                                         AND product_id=(SELECT product_id FROM PRODUCTS prod WHERE product_name='TV, electronics')),
                                        '977 mm');
INSERT INTO PRODUCTS_PARAMETERS VALUES ((SELECT product_id FROM PRODUCTS WHERE product_name = 'TV LG 43UJ630V'),
                                        (SELECT attribute_id FROM PRODUCTS_ATTRIBUTES WHERE attribute_name='Height' 
                                         AND product_id=(SELECT product_id FROM PRODUCTS prod WHERE product_name='TV, electronics')),
                                        '187 mm');
INSERT INTO PRODUCTS_PARAMETERS VALUES ((SELECT product_id FROM PRODUCTS WHERE product_name = 'TV LG 43UJ630V'),
                                        (SELECT attribute_id FROM PRODUCTS_ATTRIBUTES WHERE attribute_name='Price' 
                                         AND product_id=(SELECT product_id FROM PRODUCTS prod WHERE product_name='TV, electronics')),
                                        '1900');

INSERT INTO FEEDBACK VALUES(DEFAULT, 
                            (SELECT USER_ID FROM USERS WHERE USER_NAME='test1'), 
                            (SELECT PRODUCT_ID FROM PRODUCTS WHERE PRODUCT_NAME='Apple MacBook Air Retina 13"'),
                            5.0, 'Test feedback message');
INSERT INTO FEEDBACK VALUES(DEFAULT, 
                            (SELECT USER_ID FROM USERS WHERE USER_NAME='test2'), 
                            (SELECT PRODUCT_ID FROM PRODUCTS WHERE PRODUCT_NAME='Apple MacBook Air Retina 13"'),
                            4.5, 'Test feedback message');    
INSERT INTO FEEDBACK VALUES(DEFAULT, 
                            (SELECT USER_ID FROM USERS WHERE USER_NAME='test3'), 
                            (SELECT PRODUCT_ID FROM PRODUCTS WHERE PRODUCT_NAME='Apple MacBook Air Retina 13"'),
                            4.2, 'Test feedback message');

INSERT INTO FEEDBACK VALUES(DEFAULT, 
                            (SELECT USER_ID FROM USERS WHERE USER_NAME='test1'), 
                            (SELECT PRODUCT_ID FROM PRODUCTS WHERE PRODUCT_NAME='Apple MacBook Pro Retina 15"'),
                            4.8, 'Test feedback message');
INSERT INTO FEEDBACK VALUES(DEFAULT, 
                            (SELECT USER_ID FROM USERS WHERE USER_NAME='test2'), 
                            (SELECT PRODUCT_ID FROM PRODUCTS WHERE PRODUCT_NAME='Apple MacBook Pro Retina 15"'),
                            3.9, 'Test feedback message');    
INSERT INTO FEEDBACK VALUES(DEFAULT, 
                            (SELECT USER_ID FROM USERS WHERE USER_NAME='test3'), 
                            (SELECT PRODUCT_ID FROM PRODUCTS WHERE PRODUCT_NAME='Apple MacBook Pro Retina 15"'),
                            4.4, 'Test feedback message');

INSERT INTO FEEDBACK VALUES(DEFAULT, 
                            (SELECT USER_ID FROM USERS WHERE USER_NAME='test1'), 
                            (SELECT PRODUCT_ID FROM PRODUCTS WHERE PRODUCT_NAME='Apple iMac 21.5" Retina 4K'),
                            2.9, 'Test feedback message');
INSERT INTO FEEDBACK VALUES(DEFAULT, 
                            (SELECT USER_ID FROM USERS WHERE USER_NAME='test2'), 
                            (SELECT PRODUCT_ID FROM PRODUCTS WHERE PRODUCT_NAME='Apple iMac 21.5" Retina 4K'),
                            3.3, 'Test feedback message');    
INSERT INTO FEEDBACK VALUES(DEFAULT, 
                            (SELECT USER_ID FROM USERS WHERE USER_NAME='test3'), 
                            (SELECT PRODUCT_ID FROM PRODUCTS WHERE PRODUCT_NAME='Apple iMac 21.5" Retina 4K'),
                            4.1, 'Test feedback message');  
                            
INSERT INTO FEEDBACK VALUES(DEFAULT, 
                            (SELECT USER_ID FROM USERS WHERE USER_NAME='test1'), 
                            (SELECT PRODUCT_ID FROM PRODUCTS WHERE PRODUCT_NAME='Apple iPhone 8 256Gb RED'),
                            2.9, 'Test feedback message');
INSERT INTO FEEDBACK VALUES(DEFAULT, 
                            (SELECT USER_ID FROM USERS WHERE USER_NAME='test2'), 
                            (SELECT PRODUCT_ID FROM PRODUCTS WHERE PRODUCT_NAME='Apple iPhone 8 256Gb RED'),
                            3.7, 'Test feedback message');    
INSERT INTO FEEDBACK VALUES(DEFAULT, 
                            (SELECT USER_ID FROM USERS WHERE USER_NAME='test3'), 
                            (SELECT PRODUCT_ID FROM PRODUCTS WHERE PRODUCT_NAME='Apple iPhone 8 256Gb RED'),
                            4.3, 'Test feedback message');
                            
INSERT INTO FEEDBACK VALUES(DEFAULT, 
                            (SELECT USER_ID FROM USERS WHERE USER_NAME='test1'), 
                            (SELECT PRODUCT_ID FROM PRODUCTS WHERE PRODUCT_NAME='Apple iPhone 10 256Gb Space Gray'),
                            4.9, 'Test feedback message');
INSERT INTO FEEDBACK VALUES(DEFAULT, 
                            (SELECT USER_ID FROM USERS WHERE USER_NAME='test2'), 
                            (SELECT PRODUCT_ID FROM PRODUCTS WHERE PRODUCT_NAME='Apple iPhone 10 256Gb Space Gray'),
                            4.7, 'Test feedback message');    
INSERT INTO FEEDBACK VALUES(DEFAULT, 
                            (SELECT USER_ID FROM USERS WHERE USER_NAME='test3'), 
                            (SELECT PRODUCT_ID FROM PRODUCTS WHERE PRODUCT_NAME='Apple iPhone 10 256Gb Space Gray'),
                            5.0, 'Test feedback message');
                            
INSERT INTO FEEDBACK VALUES(DEFAULT, 
                            (SELECT USER_ID FROM USERS WHERE USER_NAME='test1'), 
                            (SELECT PRODUCT_ID FROM PRODUCTS WHERE PRODUCT_NAME='Apple iPhone 7 128Gb Space Gray'),
                            4.4, 'Test feedback message');
INSERT INTO FEEDBACK VALUES(DEFAULT, 
                            (SELECT USER_ID FROM USERS WHERE USER_NAME='test2'), 
                            (SELECT PRODUCT_ID FROM PRODUCTS WHERE PRODUCT_NAME='Apple iPhone 7 128Gb Space Gray'),
                            4.8, 'Test feedback message');    
INSERT INTO FEEDBACK VALUES(DEFAULT, 
                            (SELECT USER_ID FROM USERS WHERE USER_NAME='test3'), 
                            (SELECT PRODUCT_ID FROM PRODUCTS WHERE PRODUCT_NAME='Apple iPhone 7 128Gb Space Gray'),
                            3.7, 'Test feedback message');   
                            
INSERT INTO FEEDBACK VALUES(DEFAULT, 
                            (SELECT USER_ID FROM USERS WHERE USER_NAME='test1'), 
                            (SELECT PRODUCT_ID FROM PRODUCTS WHERE PRODUCT_NAME='TV LG 43UJ630V'),
                            4.8, 'Test feedback message');
INSERT INTO FEEDBACK VALUES(DEFAULT, 
                            (SELECT USER_ID FROM USERS WHERE USER_NAME='test2'), 
                            (SELECT PRODUCT_ID FROM PRODUCTS WHERE PRODUCT_NAME='TV LG 43UJ630V'),
                            4.5, 'Test feedback message');    
INSERT INTO FEEDBACK VALUES(DEFAULT, 
                            (SELECT USER_ID FROM USERS WHERE USER_NAME='test3'), 
                            (SELECT PRODUCT_ID FROM PRODUCTS WHERE PRODUCT_NAME='TV LG 43UJ630V'),
                            5.0, 'Test feedback message');                                                                    
END;