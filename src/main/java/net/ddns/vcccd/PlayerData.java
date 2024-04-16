package net.ddns.vcccd;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;


public class PlayerData {
	
	private final Main main;
	private Player player;
	private Connection conn;
	private int CookingEXP;
	private String CookQuality;
	
	public PlayerData(Main main, Player player) {
		this.main = main;
		this.player = player;
		this.conn = this.main.getPublicConnection();
		
		Statement sqlExecutors;
		try {
			sqlExecutors = this.conn.createStatement();
			String playerUUID = this.player.getUniqueId().toString();
			String retrieveDataSQL = "SELECT * FROM PlayerData WHERE uuid = " + "'" + playerUUID + "'";
			ResultSet resultSet = sqlExecutors.executeQuery(retrieveDataSQL);
			if(!resultSet.next()) {
				String insertData = "INSERT INTO PlayerData (uuid, cookingexp) \n" + "VALUES (" + "'" + playerUUID + "'" +", 0)";
				sqlExecutors.execute(insertData);
			} else {
                // Process the result set
				do {
		            // Retrieve data from the result set
					if(player.getUniqueId().toString().equals(resultSet.getString("uuid"))) {
						this.CookingEXP = resultSet.getInt("cookingexp");
						break;
					}

		      
		        } while (resultSet.next());

				int homemadeExp = main.getConfig().getInt("HomemadeEXP");
				int tastyExp = main.getConfig().getInt("TastyEXP");
				int delusciousExp = main.getConfig().getInt("DelusciousEXP");
				int gourmetExp = main.getConfig().getInt("GourmetEXP");
				int restaurantQualityExp = main.getConfig().getInt("RestaurantQualityEXP");
				int oneStarExp = main.getConfig().getInt("OneStarEXP");
				int twoStarExp = main.getConfig().getInt("TwoStarEXP");
				int threeStarExp = main.getConfig().getInt("ThreeStarEXP");
				int legendaryExp = main.getConfig().getInt("LegendaryEXP");

				if (this.CookingEXP < homemadeExp) {
				    this.CookQuality = ChatColor.translateAlternateColorCodes('&', "&8Quality: &lBasically Cardboard");
				} else if (this.CookingEXP >= homemadeExp && this.CookingEXP < tastyExp) {
				    this.CookQuality = ChatColor.translateAlternateColorCodes('&', "&8Quality: &2Home-Made");
				} else if (this.CookingEXP >= tastyExp && this.CookingEXP < delusciousExp) {
				    this.CookQuality = ChatColor.translateAlternateColorCodes('&', "&8Quality: &6Tasty");
				} else if (this.CookingEXP >= delusciousExp && this.CookingEXP < gourmetExp) {
				    this.CookQuality = ChatColor.translateAlternateColorCodes('&', "&8Quality: &eDeluscious");
				} else if (this.CookingEXP >= gourmetExp && this.CookingEXP < restaurantQualityExp) {
				    this.CookQuality = ChatColor.translateAlternateColorCodes('&', "&8Quality: &bGourmet");
				} else if (this.CookingEXP >= restaurantQualityExp && this.CookingEXP < oneStarExp) {
				    this.CookQuality = ChatColor.translateAlternateColorCodes('&', "&8Quality: &9Restaurant Quality");
				} else if (this.CookingEXP >= oneStarExp && this.CookingEXP < twoStarExp) {
				    this.CookQuality = ChatColor.translateAlternateColorCodes('&', "&8Quality: &3One Star");
				} else if (this.CookingEXP >= twoStarExp && this.CookingEXP < threeStarExp) {
				    this.CookQuality = ChatColor.translateAlternateColorCodes('&', "&8Quality: &3Two Star");
				} else if (this.CookingEXP >= threeStarExp && this.CookingEXP < legendaryExp) {
				    this.CookQuality = ChatColor.translateAlternateColorCodes('&', "&8Quality: &5Three Star");
				} else if (this.CookingEXP >= legendaryExp) {
				    this.CookQuality = ChatColor.translateAlternateColorCodes('&', "&8Quality: &6Ledgendary");
				}
				resultSet.close();
			    sqlExecutors.close();
			}
			
		} catch (SQLException e) {
			
			this.main.getConsole().sendMessage(ChatColor.RED + "Database machine broke... Contact dev about PlayerData class.");
		}
		
    }
	
	public int getCookingEXP() {
		return(this.CookingEXP);
	}
	
	public void setCookingExp(int newLevel) {
	    try {
	        // Create a statement
	        Statement sqlExecutors = conn.createStatement();

	        // Retrieve player's UUID
	        String playerUUID = player.getUniqueId().toString();

	        // Check if the player exists in the table
	        String retrieveDataSQL = "SELECT * FROM PlayerData WHERE uuid = '" + playerUUID + "'";
	        ResultSet resultSet = sqlExecutors.executeQuery(retrieveDataSQL);

	        // Check if the player exists
	        if (!resultSet.next()) {
	            // Player doesn't exist, insert a new record
	            String insertData = "INSERT INTO PlayerData (uuid, cookingexp) VALUES ('" + playerUUID + "', " + newLevel + ")";
	            sqlExecutors.executeUpdate(insertData);
	        } else {
	            // Player exists, update the cooking experience
	            String updateDataSQL = "UPDATE PlayerData SET cookingexp = " + newLevel + " WHERE uuid = '" + playerUUID + "'";
	            sqlExecutors.executeUpdate(updateDataSQL);
	        }

	        // Close the result set and statement
	        resultSet.close();
	        sqlExecutors.close();
	    } catch (SQLException e) {
	        // Handle SQLException
	        e.printStackTrace();
	        main.getConsole().sendMessage(ChatColor.RED + "Database machine broke... Contact dev about PlayerData class.");
	    }
	}

	
	public String getCookQuality() {
		return(this.CookQuality);
	}
	
	public String getChef() {
		return(ChatColor.translateAlternateColorCodes('&', "&8Cooked By: ") + ChatColor.YELLOW + this.player.getName());
	}
	
}
