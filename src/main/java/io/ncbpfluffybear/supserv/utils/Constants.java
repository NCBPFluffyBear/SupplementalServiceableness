package io.ncbpfluffybear.supserv.utils;

import org.bukkit.Bukkit;

public final class Constants {

  public static final int SERVER_VERSION = Integer.parseInt(Bukkit.getVersion().replaceFirst(".*MC: ", "").replace(
          ")", "").replace(".", ""));
}
