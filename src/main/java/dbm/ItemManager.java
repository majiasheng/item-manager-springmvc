package dbm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Item;

public class ItemManager {
	
	private static final String TABLE = "item";
	
	public static int addItem(Item item) {
		Connection conn = DBConnection.connect();
		String query = "INSERT INTO " + TABLE + " (name, type, price, dateAcquired) VALUES (?,?,?,?)";
		
		try {
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setString(1, item.getName());
			stmt.setString(2, item.getType());
			stmt.setInt(3, item.getPrice());
			stmt.setDate(4, new java.sql.Date(item.getDateAcquired().getTime()));
			int rtval = stmt.executeUpdate();
			conn.commit();
			return rtval;
		} catch (SQLException e) {	
			e.printStackTrace();
		} finally {
			
		}
		
		return -1;
	}
	
	public static Item getItemById(int id) {
		Item item = null;
		
		// get connection
		Connection conn = DBConnection.connect();
		
		// query
		String query = "SELECT * FROM " + TABLE + " WHERE id = ? LIMIT 1";
		
		try {
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			conn.commit();
			while(rs.next()) {
				item = new Item(rs.getString("name"), rs.getString("type"), rs.getInt("price"), rs.getDate("dateAcquired"));
				item.setId(rs.getInt("id"));
				// limit 1
				break;
			}
			
			System.out.println("[DEBUG] getting item: " + item);
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			// close connection
		}
		
		return item;
	}
	
	public static int updateItem(Item item) {
		Connection conn = DBConnection.connect();
		String query = "UPDATE " + TABLE + " SET name=?, type=?, dateAcquired=?, price=? WHERE id=?;";
		
		try {
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setString(1, item.getName());
			stmt.setString(2, item.getType());
			stmt.setDate(3, new java.sql.Date(item.getDateAcquired().getTime()));
			stmt.setInt(4, item.getPrice());
			stmt.setInt(5, item.getId());
			
			System.out.println("[DEBUG]: ***********"+stmt.toString());
			
			int rtval = stmt.executeUpdate();
			conn.commit();
			return rtval;
			
		} catch (SQLException e) {

			e.printStackTrace();
		}
		
		return -1;
	}
	
	public static int removeItem(int id) {
		Connection conn = DBConnection.connect();
		String query = "DELETE FROM " + TABLE + " WHERE id = ?";
		
		try {
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setInt(1, id);
			int rtval =  stmt.executeUpdate();
			conn.commit();
			return rtval;
		} catch (SQLException e) {			
			e.printStackTrace();
		} finally {
			//TODO: close connection, commit transaction and rollback when exception
		}
		
		return -1;
	}
	
	public static List<Item> getItems() {
		Connection conn = DBConnection.connect();
		String query = "SELECT * FROM " + TABLE;
		
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			conn.commit();
			
			List<Item> items = new ArrayList<Item>();
			while(rs.next()) {
				Item item = new Item(rs.getString("name"), rs.getString("type"), rs.getInt("price"), rs.getDate("dateAcquired"));
				item.setId(rs.getInt("id"));
				items.add(item);
				
				System.out.println("[DEBUG]: retrieving item: " + item);
			}
			
			return items;
		} catch (SQLException e) {			
			e.printStackTrace();
		} finally {
			//TODO: close connection, commit transaction and rollback when exception
		}
		
		return null;
	}
	
//	public static Item getItemByName(String name) {
//		
//	}
}
