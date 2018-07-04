package lk.ech.app.utility;

import lk.ech.app.domain.ProfessionalCenter;
import lk.ech.app.domain.Professionals;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TreeMap;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

import static lk.ech.app.utility.DBUtility.getDBConnection;

/**
 * Created by se-9 on 1/8/2017.
 */
public class Utilities {

    public int no_weeks;

    public Utilities() {
        no_weeks = 8;
    }

    public String formatTime(int hrs, int mts) {
        String s_hrs = "" + hrs;
        String s_mts = "" + mts;
        if (s_hrs.length() == 1) {
            s_hrs = "0" + s_hrs;
        }
        if (s_mts.length() == 1) {
            s_mts = "0" + s_mts;
        }
        return s_hrs + ":" + s_mts;
    }
    public String formatTime(String stTime) {
        if (stTime.length() == 3) {
            stTime = "0" + stTime;
        }
        if (stTime.length() == 2) {
            stTime = "00" + stTime;
        }
        if (stTime.length() == 1) {
            stTime = "000" + stTime;
        }
        if (stTime.length() == 0) {
            stTime = "0000" + stTime;
        }
        stTime = stTime.substring(0, 2) + ":" + stTime.substring(2, 4);
        return stTime;
    }

    public ProfessionalCenter getCenterDetails(String insCode) {
        ProfessionalCenter astroCenter = new ProfessionalCenter();
        PreparedStatement ps = null;
        Connection conn = null;
        try {
            ResultSet rs = null;

            String sQuery = "SELECT CENTER_NAME,SHORT_CODE,ADDRESS1,ADDRESS2,ADDRESS3,CITY,STATUS,SERVICE_CODE FROM service_center WHERE CENTER_CODE = '"+insCode+"'";
            conn = getDBConnection();
            ps = conn.prepareStatement(sQuery);
            rs = ps.executeQuery();
            while (rs.next()) {
                astroCenter.setCenter_Name(rs.getString("CENTER_NAME"));
                astroCenter.setShort_Code(rs.getString("SHORT_CODE"));
                astroCenter.setAddress1(rs.getString("ADDRESS1"));
                astroCenter.setAddress2(rs.getString("ADDRESS2"));
                astroCenter.setAddress3(rs.getString("ADDRESS3"));
                astroCenter.setCity(rs.getString("CITY"));
                astroCenter.setStatus(rs.getString("STATUS"));
                astroCenter.setServiceCode(rs.getInt("SERVICE_CODE"));

            }
        } catch (SQLException e) {
            e.printStackTrace();//To change body of catch statement use File | Settings | File Templates.
            Logger.getLogger("server.log").log(Level.WARNING, "\nException:{0}", e.getMessage());
        }
        finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return astroCenter;
    }

    public Vector getSpecsForAvailableAstrologerVector() throws SQLException {
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet resultset = null;
        Vector vector = new Vector();
        try {
            conn = getDBConnection();
            String s = "select distinct s.DESCRIPTION,s.PROFESSIONAL_SPEC_CODE,s.STATUS from service_specialization s where STATUS = 1 ORDER BY s.PROFESSIONAL_SPEC_CODE ASC";
            String s1;
            String s2;
            String s3;
            ps = conn.prepareStatement(s);
            resultset =ps.executeQuery();
            while (resultset.next()) {
                s1 = resultset.getString("DESCRIPTION");
                s2 = resultset.getString("PROFESSIONAL_SPEC_CODE");
                s3 = resultset.getString("STATUS");
                vector.add(s1 + "~" + s2);
            }

            resultset.close();
        }
        catch (SQLException e) {
            e.printStackTrace();//To change body of catch statement use File | Settings | File Templates.
            Logger.getLogger("server.log").log(Level.WARNING, "\nException : {0}", e.getMessage());
            throw e;
        }
        finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return vector;
    }

    public Vector getAvailableAstrologerVector(String centerCode) throws SQLException {
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet resultset = null;
        Vector vector = new Vector();
        try {
            conn = getDBConnection();
            String s = "SELECT DISTINCT (d.PROFESSIONAL_CODE) as astroID, concat(d.FIRST_NAME ,' ', d.SURNAME) as name FROM service_professional d, service_session A WHERE A.CENTER_CODE = '"+centerCode+"' AND A.PROFESSIONAL_CODE = d.PROFESSIONAL_CODE and d.STATUS=1 order by name";
            String s1;
            String s2;
            ps = conn.prepareStatement(s);
            resultset = ps.executeQuery();
            while (resultset.next()) {
                s1 = resultset.getString("astroID");
                s2 = resultset.getString("name");
                vector.add(s2 + "~" + s1);
            }

            resultset.close();
        }
        catch (SQLException e) {
            e.printStackTrace();//To change body of catch statement use File | Settings | File Templates.
            Logger.getLogger("server.log").log(Level.WARNING, "\nException : {0}", e.getMessage());
            throw e;
        }
        finally {
            try
            {
                try {
                    if (ps != null) {
                        ps.close();
                    }
                    if (conn != null) {
                        conn.close();
                    }
                }
                catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            catch( Exception e )
            {
                e.printStackTrace();//To change body of catch statement use File | Settings | File Templates.
                Logger.getLogger("server.log").log( Level.WARNING, "Exception :{0}", e.getMessage() );

            }
        }
        return vector;
    }

    public Vector getAllAstrologersVector(String spec_code ,String centerCode) {
        String quary = "";
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;

        Vector vector = new Vector();

        quary = " SELECT Distinct(A.PROFESSIONAL_CODE) as astroID, concat(d.SURNAME,'=',d.FIRST_NAME,'=',d.TITLE)  as name FROM service_session A, service_professional d WHERE  A.SPEC_CODE='"+spec_code+"' AND A.PROFESSIONAL_CODE = d.PROFESSIONAL_CODE  and A.STATUS in (1,3) order by name";

        try
        {
            conn = getDBConnection();
            ps = conn.prepareStatement(quary);
            rs = ps.executeQuery();
            while (rs.next()) {
                vector.add(rs.getString("name")+"~"+rs.getString("astroID"));

            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            Logger.getLogger("server.log").log(Level.WARNING, "\nException:{0}", e.getMessage());
        }
        finally {

            try {
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return vector;

    }

    public String getAstrologerNameByAstrologerID(String astroID) {
        String fullName;
        String query;
        PreparedStatement ps = null;
        Connection conn = null;
        fullName = "--";
        if (astroID == null || astroID.trim().equals("")) {
            return astroID;
        }
        if (astroID != null && !astroID.equals("")) {
            query = "SELECT concat(d.TITLE,' ', d.FIRST_NAME ,' ', d.SURNAME) astrologerName FROM service_professional d  where d.PROFESSIONAL_CODE = '" + astroID + "'";

            ResultSet rs = null;
            conn = getDBConnection();


            try {
                ps = conn.prepareStatement(query);
                rs = ps.executeQuery();
                if (rs.next()) {
                    fullName = rs.getString("astrologerName");
                }
            }
            catch (SQLException e) {
                e.printStackTrace();//To change body of catch statement use File | Settings | File Templates.
                Logger.getLogger("server.log").log(Level.WARNING, "\nException :{0}", e.getMessage());
                fullName = "--";
            }
            finally {
                try {
                    if (ps != null) {
                        ps.close();
                    }
                    if (conn != null) {
                        conn.close();
                    }
                }
                catch (SQLException e) {
                    e.printStackTrace();
                }

            }
        } else {
            fullName = "--";
        }

        return fullName;
    }

    public String getSpecialityNameBySpecCode(String astroCode) {
        String fullName;
        String query;
        fullName = "--";
        PreparedStatement ps = null;
        Connection conn = null;
        if (astroCode == null || astroCode.trim().equals("")) {
            return astroCode;
        }
        if (astroCode != null && !astroCode.equals("")) {
            query = "SELECT d.DESCRIPTION as description FROM service_specialization d where d.PROFESSIONAL_SPEC_CODE = '" + astroCode + "' and STATUS=1 ";

            ResultSet rs = null;


            try {
                conn = getDBConnection();
                ps = conn.prepareStatement(query);
                rs = ps.executeQuery();
                if (rs.next()) {
                    fullName = rs.getString("description");
                }
            }
            catch (SQLException e) {
                e.printStackTrace();//To change body of catch statement use File | Settings | File Templates.
                Logger.getLogger("server.log").log(Level.WARNING, "\nException :{0}", e.getMessage());
                fullName = "--";
            }
            finally {
                try {
                    if (ps != null) {
                        ps.close();
                    }
                    if (conn != null) {
                        conn.close();
                    }
                }
                catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } else {
            fullName = "--";
        }

        return fullName;
    }
    public String getSessionDetails(String sessionId1){
         String sessionId = sessionId1;
                if (sessionId.length()==1) {
                    sessionId = "0000000"+sessionId;
                }
                else if (sessionId.length()==2) {
                    sessionId = "000000"+sessionId;
                }else if (sessionId.length()==3) {
                    sessionId = "00000"+sessionId;
                }
                else if (sessionId.length()==4) {
                    sessionId = "0000"+sessionId;
                }
                else if (sessionId.length()==5) {
            sessionId = "000"+sessionId;
                }
                else if (sessionId.length()==6) {
            sessionId = "00"+sessionId;
                }
                else if (sessionId.length()==7) {
                    sessionId = "0"+sessionId;
                }
                else if (sessionId.length()==8) {
                    sessionId = ""+sessionId;
                }
        String sessionDetail = "--";
        String query;
        PreparedStatement ps = null;
        Connection conn = null;
        if (sessionId == null || sessionId.trim().equals("")) {
            return sessionId;
        }

        if (sessionId != null && !sessionId.equals("")) {
            query = "SELECT START_TIME,PROFESSIONAL_CODE,CENTER_CODE,DAY FROM service_session  where SESSION_ID = '" + sessionId + "'";

            ResultSet rs = null;
            conn = getDBConnection();


            try {
                ps = conn.prepareStatement(query);
                rs = ps.executeQuery();
                if (rs.next()) {
                    sessionDetail = rs.getString("START_TIME") +"~"+ rs.getString("PROFESSIONAL_CODE") + "~" +rs.getString("CENTER_CODE") ;
                }
            }
            catch (SQLException e) {
                e.printStackTrace();//To change body of catch statement use File | Settings | File Templates.
                Logger.getLogger("server.log").log(Level.WARNING, "\nException :{0}", e.getMessage());
                sessionDetail = "--";
            }
            finally {
                try {
                    if (ps != null) {
                        ps.close();
                    }
                    if (conn != null) {
                        conn.close();
                    }
                }
                catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } else {
            sessionDetail = "--";
        }

        return sessionDetail;
    }

    public ArrayList <String> getSpecialityForAstrologer(String astrologerCode) {
        ArrayList <String> spec = new ArrayList <String>();
        String quary = "";
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;

        quary = "SELECT SPEC_CODE FROM service_professional_spec WHERE PROFESSIONAL_CODE = '"+astrologerCode+"'";
        try
        {
            conn = getDBConnection();
            ps = conn.prepareStatement(quary);
            rs = ps.executeQuery();
            while (rs.next()){
                spec.add(getSpecialityNameBySpecCode(rs.getString("SPEC_CODE"))+"~"+rs.getString("SPEC_CODE"));
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            Logger.getLogger("server.log").log(Level.WARNING, "\nException:{0}", e.getMessage());
        }
        finally {

            try {
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return spec;

    }

    /*public Vector getAstrologerAvailabilityVector(String astrologerID, String insCode) throws SQLException {
        EChannelDB con = new EChannelDB();
        EChannelDB con2 = new EChannelDB();
        ResultSet rs = null;
        ResultSet rs_sub = null;
        Vector vect = new Vector();
        con.connectToDB();
        con2.connectToDB();
        String sQuery = "";
        for (int i = 0; i <= no_weeks; i++) {
            sQuery = sQuery + " select (A.ASTRO_CHARGE + A.CENTER_CHARGE) A_FEE ,A.STATUS, A.WEB_CLIENTS, A.TIME_INTERVAL,A.START_TIME, A.NO_OF_CLIENTS,A.DAY,";
            sQuery = sQuery + "to_char(Next_Day(sysdate-1+";
            sQuery = sQuery + i * 7;
            sQuery = sQuery + " ,A.DAY),'DD-MM-YYYY') APP_DATE1, ";
            sQuery = sQuery + " Next_Day(sysdate-1+";
            sQuery = sQuery + i * 7;
            sQuery = sQuery + " ,A.DAY) APP_DATE2 ";
            sQuery = sQuery + " from service_session A ";
            sQuery = sQuery + " where  A.CENTER_CODE ='";
            sQuery = sQuery + insCode + "'";
            sQuery = sQuery + " and A.PROFESSIONAL_CODE ='";
            sQuery = sQuery + astrologerID + "'";
            *//*sQuery = sQuery + "' and Next_Day(sysdate-1+";
            sQuery = sQuery + i * 7;
            sQuery = sQuery + " ,A.DAY)<(sysdate+A.ADVANCED_CH_DATE+1) ";*//*
            sQuery = sQuery + " UNION ";
        }

        sQuery = sQuery.substring(0, sQuery.length() - 7);
        System.out.println("-------------------------------------"+sQuery);
        sQuery = sQuery + " Order by  APP_DATE2,START_TIME ";
        for (rs = con.query(sQuery); rs.next();) {
            String day = rs.getString("DAY");
            String appDate = rs.getString("APP_DATE1");
            int max_no_of_patients = rs.getInt("NO_OF_CLIENTS");
            int time_interval = rs.getInt("TIME_INTERVAL");
            int count_all = 0;
            int count_active = 0;
            int appNO = 0;
            int max_no_web_patients = rs.getInt("WEB_CLIENTS");
            int no_web_patients = 0;
            int status = rs.getInt("STATUS");
            int no_bnk_patients = 0;
            int fee = rs.getInt("A_FEE");
            String stTime = rs.getString("START_TIME");



            if (status == 1 || status == 3) {
                sQuery = "select A.CHANNEL_FROM, A.STATUS,A.APP_NO  from ASTRO_appointments A  where A.ins_code='" + insCode + "' and A.PROFESSIONAL_CODE = '" + astrologerID + "' and A.app_date = to_date('" + appDate + "','DD-MM-YYYY')";
                for (rs_sub = con2.query(sQuery); rs_sub.next();) {
                    count_all++;
                    if (rs_sub.getInt("STATUS") == 1 || rs_sub.getInt("STATUS") == 8 || rs_sub.getInt("STATUS") == 9) {
                        count_active++;
                    }
                    if (rs_sub.getInt("STATUS") == 1 && (rs_sub.getString("CHANNEL_FROM").equals("W") || rs_sub.getString("CHANNEL_FROM").equals("B"))) {
                        no_web_patients++;
                    }
                    if (rs_sub.getInt("STATUS") == 1 && rs_sub.getString("CHANNEL_FROM").equals("B")) {
                        no_bnk_patients++;
                    }
                    appNO = rs_sub.getInt("APP_NO");
                }

                rs_sub.close();
                stTime = formatTime(stTime);
                vect.add(day + "~" + appDate + "~" + stTime + "~" + max_no_of_patients + "~" + time_interval + "~" + count_all + "~" + count_active + "~" + appNO + "~" + max_no_web_patients + "~" + no_web_patients + "~" + status + "~" + no_bnk_patients + "~" + fee  );

            }

        }

        rs.close();
        con.flushStmtRs();
        con2.flushStmtRs();
        return vect;
    }*/

    public Vector getAstrologerAvailabilityVector(String astrologerID, String insCode) throws SQLException {
        Connection con = getDBConnection();
        ResultSet rs = null;
        PreparedStatement ps = null;
        ResultSet rs_sub = null;
        int countRows=0;
        Vector vect = new Vector();
        String sQuery= "";

        /*sQuery = sQuery + " select A.Session_ID, DATE_FORMAT(A.APP_DATE,'%Y-%m-%d')APP_DATE1,A.CENTER_CHARGE ,A.STATUS, A.WEB_CLIENTS, A.TIME_INTERVAL,A.START_TIME, A.NO_OF_CLIENTS,A.DAY,A.ADVANCED_CH_DATE";
        sQuery = sQuery + " from service_session A ";
        sQuery = sQuery + " where  A.CENTER_CODE ='";
        sQuery = sQuery + insCode + "'";
        sQuery = sQuery + " and A.PROFESSIONAL_CODE ='";
        sQuery = sQuery + astrologerID + "'";
        sQuery = sQuery +"and STR_TO_DATE(concat_ws(' ',A.APP_DATE,TIME_FORMAT(concat(A.START_TIME,'00'),'%H%i')),'%Y-%m-%d %H%i') >=STR_TO_DATE(DATE_FORMAT(now(),'%Y-%m-%d %H%i') , '%Y-%m-%d %H%i') ";
        sQuery = sQuery + " Order by 2 ASC,7 ASC";
        */
        sQuery = sQuery + " select A.Session_ID, DATE_FORMAT(A.APP_DATE,'%Y-%m-%d')APP_DATE1,A.CENTER_CHARGE ,A.STATUS, A.WEB_CLIENTS, A.TIME_INTERVAL,A.START_TIME, A.NO_OF_CLIENTS,A.DAY,A.ADVANCED_CH_DATE";
        sQuery = sQuery + " from service_session A ";
        sQuery = sQuery + " where  A.CENTER_CODE ='";
        sQuery = sQuery + insCode + "'";
        sQuery = sQuery + " and A.PROFESSIONAL_CODE ='";
        sQuery = sQuery + astrologerID + "'";
        sQuery = sQuery +"and STR_TO_DATE(concat_ws(' ',A.APP_DATE,TIME_FORMAT(concat(A.FINISH_TIME,'00'),'%H%i')),'%Y-%m-%d %H%i') >=STR_TO_DATE(DATE_FORMAT(now(),'%Y-%m-%d %H%i') , '%Y-%m-%d %H%i') ";
        //sQuery = sQuery +"and STR_TO_DATE(APP_DATE ,'%Y-%m-%d')>= STR_TO_DATE(now() ,'%Y-%m-%d') and Time_FORMAT(timediff(TIME_FORMAT(concat(START_TIME,'00'),'%H%i') , TIME_FORMAT(now(),'%H%i')) ,'%H%i') > -0005 ";
        sQuery = sQuery + " Order by 2 ASC,7 ASC";
        ps = con.prepareStatement(sQuery);
        L1:for (rs = ps.executeQuery(); rs.next();) {
            int session_ID = rs.getInt("Session_ID");
            String day = rs.getString("DAY");
            String appDate = rs.getString("APP_DATE1");
            int max_no_of_patients = rs.getInt("NO_OF_CLIENTS");
            int time_interval = rs.getInt("TIME_INTERVAL");
            int count_all = 0;
            int count_active = 0;
            int appNO = 0;
            int max_no_web_patients = rs.getInt("WEB_CLIENTS");
            int no_web_patients = 0;
            int status = rs.getInt("STATUS");
            int no_bnk_patients = 0;
            int fee = rs.getInt("CENTER_CHARGE");
            String stTime = rs.getString("START_TIME");



            if (status == 1 || status == 3) {

                sQuery = "select A.CHANNEL_FROM, A.STATUS,A.APP_NO  from service_appointment A  where A.CENTER_CODE='" + insCode + "' and A.PROFESSIONAL_CODE = '" + astrologerID + "' and SESSION_ID =" + session_ID;
                ps = con.prepareStatement(sQuery);
                for (rs_sub = ps.executeQuery(); rs_sub.next();) {
                    count_all++;
                    if (rs_sub.getInt("STATUS") == 1 || rs_sub.getInt("STATUS") == 8 || rs_sub.getInt("STATUS") == 9) {
                        count_active++;
                    }
                    if (rs_sub.getInt("STATUS") == 1 && (rs_sub.getString("CHANNEL_FROM").equals("W") || rs_sub.getString("CHANNEL_FROM").equals("B"))) {
                        no_web_patients++;
                    }
                    appNO = rs_sub.getInt("APP_NO");
                }

                rs_sub.close();
                stTime = formatTime(stTime);
                vect.add(day + "~" + appDate + "~" + stTime + "~" + max_no_of_patients + "~" + time_interval + "~" + count_all + "~" + count_active + "~" + appNO + "~" + max_no_web_patients + "~" + no_web_patients + "~" + status + "~" + no_bnk_patients + "~" + fee + "~" + session_ID );

            }
            countRows++;

            if(countRows >8){
                break L1;
            }

        }

        rs.close();
        try {
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return vect;
    }

    public TreeMap<String,Integer> getAstrologerName () {
        TreeMap<String,Integer> astrologer = new TreeMap<String, Integer>();
        Connection con = getDBConnection();
        ResultSet rs = null;
        PreparedStatement ps = null;

        try {
            String quey = "select aa.PROFESSIONAL_CODE, aa.TITLE, aa.FIRST_NAME, aa.SURNAME from service_professional aa where aa.STATUS='1'";
            ps = con.prepareStatement(quey);
            rs = ps.executeQuery();
            while (rs.next()) {
                int astroCode = rs.getInt("PROFESSIONAL_CODE");
                String firstName = rs.getString("FIRST_NAME");
                String surName = rs.getString("SURNAME");
                StringBuffer sb=new StringBuffer(firstName+" ");
                sb.append(surName);
                astrologer.put(sb.toString(),astroCode);
            }
        }
        catch (SQLException ex) {
            ex.printStackTrace();
            Logger.getLogger("server.log").log(Level.WARNING, "SQLEXCEPTION SELECT:",ex.getMessage());
        }
        finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.close();
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return astrologer;
    }

    public TreeMap<String,String> getAstroSpec(int serviceCode) {
        TreeMap<String,String> astrospec = new TreeMap<String, String>();
        Connection con = getDBConnection();
        ResultSet rs = null;
        PreparedStatement ps = null;

        try{
            String query = "Select * from service_specialization where Status = 1 and SERVICE_CODE = '"+serviceCode+"' order by PROFESSIONAL_SPEC_CODE ASC";
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()){
                String astroCode = rs.getString(1);
                String description = rs.getString(2);
                astrospec.put(description,astroCode);
            }
        }
        catch (SQLException ex) {
            ex.printStackTrace();
            Logger.getLogger("server.log").log(Level.WARNING, "SQLEXCEPTION SELECT:",ex.getMessage());
        }
        finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.close();
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return astrospec;
    }

    public static String getDate(String dateString) {

        Date date = null;
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        try {
            date = df.parse(dateString);
        }
        catch (Exception ex) {
            ex.printStackTrace();
            Logger.getLogger("server.log").log(Level.WARNING, "Exception convert date:",ex.getMessage());
        }
        return df.format(date);
    }

    public String Format12hr(String dateStr) {
        if (dateStr.length() == 3) {
            dateStr = "0" + dateStr;
        }
        dateStr = dateStr.substring(0, 4);
        int Hrs = Integer.parseInt(dateStr.substring(0, 2));
        dateStr = dateStr.substring(2, 4);
        if (Hrs >= 12) {
            if (Hrs != 12) {
                Hrs -= 12;
            }
            dateStr = Hrs + ":" + dateStr + " p.m";
        } else {
            dateStr = Hrs +":" + dateStr + " a.m";
        }
        return dateStr;
    }
    public String getSpecName(String specId) {
        Connection con = getDBConnection();
        ResultSet rs = null;
        String description = "";
        PreparedStatement ps = null;


        try {
            String sql = "select DESCRIPTION from service_specialization where PROFESSIONAL_SPEC_CODE='"+specId+"' and status =1";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            if (rs.next()) {
                description = rs.getString("DESCRIPTION");
            }
        }
        catch (SQLException ex) {
            ex.printStackTrace();
            Logger.getLogger("server.log").log(Level.WARNING, "SQLEXCEPTION SELECT:",ex.getMessage());
        }
        finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.close();
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return description;
    }

    public Professionals getAstrologerDetails(String astrologerCode){
        Connection con = getDBConnection();
        ResultSet rs = null;
        PreparedStatement ps = null;
        Professionals astro = new Professionals();
        String sql = "SELECT * FROM service_professional WHERE PROFESSIONAL_CODE = '"+astrologerCode+"'";

        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if(rs.next()){
                String title = rs.getString("TITLE");
                String fName = rs.getString("FIRST_NAME");
                String lName = rs.getString("SURNAME");
                int status = rs.getInt("STATUS");
                int mobile = rs.getInt("MOBILENUMBER");
                astro.setTitle(title);
                astro.setFirstName(fName);
                astro.setLastName(lName);
                astro.setStatus(status);
                astro.setMobile(mobile);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.close();
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return astro;
    }
    public Vector getSpeciality(String astrologerCode){
        Connection con = getDBConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Vector vect = null;
        String sql = "SELECT distinct(SPEC_CODE) spec FROM service_professional_spec WHERE PROFESSIONAL_CODE = '"+astrologerCode+"'";

        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            vect = new Vector();
            while(rs.next()){

                vect.add(rs.getString("spec"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.close();
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return vect;
    }

    public ArrayList<String> getAvailableAstrologerForCenter(String centerCode){
        Connection con = getDBConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<String> astrologers = new ArrayList<String>();
        String sql = "SELECT distinct(PROFESSIONAL_CODE) code FROM service_professional_center WHERE CENTER_CODE = '"+centerCode+"'";

        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while(rs.next()){
                astrologers.add(rs.getString("code"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.close();
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return astrologers;

    }

    public ArrayList<String> getAstroSpecAndAstroCharge(String sessionID) {
        ArrayList<String> astrospec = new ArrayList<String>();
        Connection con = getDBConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try{
            String query = "Select ASTROSPEC_ID,SPEC_CODE,ASTRO_CHARGE from service_session_spec where SESSION_ID = '"+sessionID+"'";
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()){
                String specCode = rs.getString("SPEC_CODE");
                String astroCharge = rs.getString("ASTRO_CHARGE");
                String id = rs.getString("ASTROSPEC_ID");
                astrospec.add(specCode+"~"+astroCharge+"~"+id);
            }
        }
        catch (SQLException ex) {
            ex.printStackTrace();
            Logger.getLogger("server.log").log(Level.WARNING, "SQLEXCEPTION SELECT:",ex.getMessage());
        }
        finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.close();
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return astrospec;
    }
    public String getNote(String centerCode , String astrologerId){
        Connection con = getDBConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String note = "";

        String query = "Select NOTE from service_professional_note where PROFESSIONAL_CODE = '"+astrologerId+"'  and CENTER_ID = '"+centerCode+"'";
        try {
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()){
                note = rs.getString(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.close();
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return note;
    }

    public  ArrayList <String>  getLogos(String centerCode){
        Connection con = getDBConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList <String> arraylist = new ArrayList<String>();

        String query = "Select MainLogo,SecondLogo from service_logos where CENTERCODE = '"+centerCode+"'";
        try {
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()){
                String logo1 = rs.getString(1);
                String logo2 = rs.getString(2);
                arraylist.add(logo1+"~"+logo2+"~"+centerCode);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.close();
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return arraylist;
    }
}
