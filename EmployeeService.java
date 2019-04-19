/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jp.co.tao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 *
 * @author user
 */
public class EmployeeService {

    /**
     * 社員情報個別登録
     *
     * @param epy
     * @param emp
     * @return
     * @throws java.lang.ClassNotFoundException
     *
     */
    public boolean createEmployeeDate(Employee epy) throws ClassNotFoundException {

        try {
            //DBを読み込む
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/postgres",
                    "postgres",
                    "postgres");
            //問い合わせの準備  将SQL内容送到DB
            Statement stmtSeq = connection.createStatement();
            //id自動採番　
            ResultSet seqResutSet = stmtSeq.executeQuery("select nextval('e_id_seq');");
            seqResutSet.next();
            //検索当前行中指定列的値
            long seq = seqResutSet.getLong(1);
            //日付フォーマット
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            //現在の日付を取得
            Date date = new Date();
            //date to string
            String currentYMD = dateFormat.format(date);
            //DB open
            Statement stmt = connection.createStatement();
            String sql = "insert into employeess values('" + String.format("%05d", seq) + "'"
                    + ",'" + epy.geteName() + "'"
                    + ",'" + epy.geteSex() + "'"
                    + ",'" + epy.getePos() + "'"
                    + ",'" + currentYMD + "');";

            //DBにデータ挿入
            stmt.executeUpdate(sql);
            stmt.close();
            //データベースclose
            connection.close();
            System.out.println(sql);

        } catch (SQLException ex) {
            Logger.getLogger(EmployeeService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

    /**
     *
     * 社員情報一覧表示
     *
     * @return List
     * @throws java.sql.SQLException
     */
    public List<Employee> selectAllEmployee() throws SQLException {
        //リスト新規
        List<Employee> infoList = new ArrayList<>();
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/postgres",
                    "postgres",
                    "postgres");
            //問い合わせの準備
            Statement stmtSeq = connection.createStatement();
            //idで検索
            String sql = "select * from employeess order by e_id asc;";
            //　データベースの結果セットを表す
            ResultSet resultset = stmtSeq.executeQuery(sql);
            while (resultset.next()) {
                Employee em = new Employee();
                em.seteId(resultset.getObject("e_id").toString());
                em.seteName(resultset.getObject("e_name").toString());
                em.seteSex(resultset.getObject("e_sex").toString().equals("0") ? "女性" : "男性");
                em.setePos(resultset.getObject("e_pos").toString().equals("HR") ? "マネージャー" : "一般社員");
                //現在の時間を取る
                em.seteEntryDate(resultset.getTimestamp("e_entrydate"));
                //リストにデータを追加する
                infoList.add(em);
            }
            stmtSeq.close();
            connection.close();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EmployeeService.class.getName()).log(Level.SEVERE, null, ex);
        }

        return infoList;
    }

    /**
     * 社員情報更新、処理
     *
     * @param emp
     * @return
     */
    public boolean editEmpInfo(Employee emp) throws SQLException {
        try {
            //DBを読み込む
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/postgres",
                    "postgres",
                    "postgres");
            //問い合わせの準備  将SQL内容送到DB
            Statement stmtSeq = connection.createStatement();
            String sql = "update employeess set e_name = '" + emp.geteName() + "'"
                    + ",e_sex = '" + emp.geteSex() + "'"
                    + ",e_pos = '" + emp.getePos() + "'"
                    + "where e_id = '" + emp.geteId() + "'";
            stmtSeq.executeUpdate(sql);
            stmtSeq.close();
            connection.close();
            //System.out.println(sql);

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EmployeeService.class.getName()).log(Level.SEVERE, null, ex);
        }

        return true;

    }
}
