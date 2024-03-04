package com.kryeit.kryeit.storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TrainTrustManager {
	private final File trustFolder;

	public TrainTrustManager() {
		trustFolder = new File("mods/kryeit/traintrust/");
		if (!trustFolder.exists()) {
			trustFolder.mkdirs();
		}
	}

	public void addTrustedPlayer(UUID ownerUUID, UUID trustedUUID) {
		File ownerFile = getOwnerFile(ownerUUID);
		List<UUID> trustedPlayers = getTrustedPlayers(ownerUUID);
		if (!trustedPlayers.contains(trustedUUID)) {
			trustedPlayers.add(trustedUUID);
			writeFile(ownerFile, trustedPlayers);
		}
	}

	public void revokeTrustedPlayer(UUID ownerUUID, UUID trustedUUID) {
		File ownerFile = getOwnerFile(ownerUUID);
		List<UUID> trustedPlayers = getTrustedPlayers(ownerUUID);
		if (trustedPlayers.remove(trustedUUID)) {
			writeFile(ownerFile, trustedPlayers);
		}
	}

	public List<UUID> getTrustedPlayers(UUID ownerUUID) {
		List<UUID> trustedPlayers = new ArrayList<>();
		try {
			File ownerFile = getOwnerFile(ownerUUID);
			if (ownerFile.exists()) {
				try (BufferedReader reader = new BufferedReader(new FileReader(ownerFile))) {
					String line;
					while ((line = reader.readLine()) != null) {
						trustedPlayers.add(UUID.fromString(line));
					}
				}
			}
		} catch (IOException e) {
			System.err.println("Failed to get trusted players: " + e.getMessage());
		}
		return trustedPlayers;
	}

	private File getOwnerFile(UUID ownerUUID) {
		return new File(trustFolder, ownerUUID + ".txt");
	}

	private void writeFile(File file, List<UUID> uuids) {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, false))) { // Overwrite the file
			for (UUID uuid : uuids) {
				writer.write(uuid.toString());
				writer.newLine();
			}
		} catch (IOException e) {
			System.err.println("Failed to write file: " + e.getMessage());
		}
	}
}
