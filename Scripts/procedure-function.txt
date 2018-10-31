CREATE PROCEDURE `get_mobile_details` (	IN p_mobile_id INT(11), 
					OUT p_name VARCHAR(45), 
					OUT p_price DOUBLE, 
					OUT p_qty INT(11) )

BEGIN


	select name, price, quantity INTO p_name, p_price, p_qty

	from mobiles_cg where mobile_id=p_mobile_id;


END


====================================================================

CREATE FUNCTION `get_mobile_price` (p_mobile_id INT(11))

RETURNS DOUBLE

BEGIN
DECLARE v_price DOUBLE;
SET v_price=0.0;

select price into v_price from mobiles_cg
where mobile_id=p_mobile_id;



RETURN v_price;


END
=====================================================================

String sql="{call procedure_name(?,?)}";
String sql="{ ?=call function_name(?,?)}";