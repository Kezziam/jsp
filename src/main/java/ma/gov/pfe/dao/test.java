package ma.gov.pfe.dao;

public class test {


	public static void main(String[] args) {
    
		
//		
//		SimpleDateFormat sdf= new SimpleDateFormat("yyy-MM-dd HH:mm:ss");
//		 Date d=new Date();
//	String strng_dateTime= new SimpleDateFormat("yyy-MM-dd HH:mm:ss").format(new Date());
//	Timestamp timeStamp = java.sql.Timestamp.valueOf( strng_dateTime) ;
//		System.out.println(strng_dateTime);
//		System.out.println(timeStamp);
		
		VisiteDao vd=new VisiteDao();
		 boolean b=vd.findByRoomAbonnee(1l,1l);
		 System.out.println("============================================\n B===="+b);
	    
     
	}
    
	

}
