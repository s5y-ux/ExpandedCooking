package net.ddns.vcccd;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.sql.*;

public class Admin implements CommandExecutor {
    private Main main;

    public Admin(Main main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            try {
                // Retrieve the connection
                Connection conn = this.main.getPublicConnection();

                // Create a statement
                Statement statement = conn.createStatement();

                // Execute the query
                String query = "SELECT * FROM PlayerData";
                ResultSet resultSet = statement.executeQuery(query);

                // Process the result set
                while (resultSet.next()) {
                    // Retrieve data from the result set
                    String playerId = resultSet.getString("uuid");
                    int score = resultSet.getInt("cookingexp");

                    // Print or process the retrieved data
                    player.sendMessage("Player ID: " + playerId + ", Cooking Exp: " + score);
                }

                // Close the statement and result set
                statement.close();
                resultSet.close();
            } catch (SQLException e) {
                player.sendMessage("Problem executing SQL query.");
                e.printStackTrace();
            }
        } else {
            sender.sendMessage("You must be a player to execute this command.");
        }

        return true;
    }
}
