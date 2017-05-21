/*
 *   This file is part of Skript.
 *
 *  Skript is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  Skript is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with Skript.  If not, see <http://www.gnu.org/licenses/>.
 * 
 * 
 * Copyright 2011-2017 Peter Güttinger and contributors
 * 
 */

package ch.njol.skript.util.chat;

import org.eclipse.jdt.annotation.Nullable;

import ch.njol.skript.lang.VariableString;
import ch.njol.skript.util.chat.MessageComponent.*;

/**
 * Chat codes; includes color codes (<a href="http://wiki.vg/Chat#Colors">reference</a>)
 * and also, some formatting codes (mostly <a href="http://wiki.vg/Chat">this</a>)
 */
public enum ChatCode {
	
	reset {
		@Override
		public boolean nextComponent() {
			return true;
		}
		
		@Override
		public void updateComponent(MessageComponent component, String param) {
			component.reset = true;
		}
	},

	// Colors (Vanilla color code, Skript color code if different)
	
	black("black", '0'),
	dark_blue("dark_blue", '1'),
	dark_green("dark_green", '2'),
	dark_aqua("dark_aqua", "dark_cyan", '3'),
	dark_red("dark_red", '4'),
	dark_purple("dark_purple", '5'),
	gold("gold", "orange", '6'),
	gray("gray", "light_grey", '7'),
	dark_gray("dark_gray", "dark_grey", '8'),
	blue("blue", "light_cyan", '9'),
	green("green", "light_green", 'a'),
	aqua("aqua", "light_cyan", 'b'),
	red("red", "light_red", 'c'),
	light_purple("light_purple", 'd'),
	yellow("yellow", 'e'),
	white("white", 'f'),
	
	// Formatting
	
	bold {
		@Override
		public void updateComponent(MessageComponent component, String param) {
			component.bold = true;
		}
	},
	
	italic {
		@Override
		public void updateComponent(MessageComponent component, String param) {
			component.italic = true;
		}
	},
	
	underlined(null, "underline") {
		@Override
		public void updateComponent(MessageComponent component, String param) {
			component.underlined = true;
		}
	},
	
	strikethrough {
		@Override
		public void updateComponent(MessageComponent component, String param) {
			component.strikethrough = true;
		}
	},
	
	obfuscated(null, "magic") {
		@Override
		public void updateComponent(MessageComponent component, String param) {
			component.obfuscated = true;
		}
	},
	
	// clickEvent
	
	open_url(true) {
		@Override
		public void updateComponent(MessageComponent component, String param, @Nullable VariableString var) {
			ClickEvent e = new ClickEvent(ClickEvent.Action.open_url, param, var);
			component.clickEvent = e;
		}
	},
	
	run_command(true) {
		@Override
		public void updateComponent(MessageComponent component, String param, @Nullable VariableString var) {
			ClickEvent e = new ClickEvent(ClickEvent.Action.run_command, param, var);
			component.clickEvent = e;
		}
	},
	
	suggest_command(true) {
		@Override
		public void updateComponent(MessageComponent component, String param, @Nullable VariableString var) {
			ClickEvent e = new ClickEvent(ClickEvent.Action.suggest_command, param, var);
			component.clickEvent = e;
		}
	},
	
	change_page(true) {
		@Override
		public void updateComponent(MessageComponent component, String param, @Nullable VariableString var) {
			ClickEvent e = new ClickEvent(ClickEvent.Action.change_page, param, var);
			component.clickEvent = e;
		}
	},
	
	// hoverEvent
	
	show_text(true) {
		@Override
		public void updateComponent(MessageComponent component, String param, @Nullable VariableString var) {
			HoverEvent e = new HoverEvent(HoverEvent.Action.show_text, param, var);
			component.hoverEvent = e;
		}
	};
	
	public boolean hasParam;
	
	@Nullable
	public String colorCode;
	
	@Nullable
	public String langName;
	
	public char colorChar;
	
	ChatCode(@Nullable String colorCode, String langName, char colorChar) {
		this.colorCode = colorCode;
		this.langName = langName;
		this.hasParam = false;
		this.colorChar = colorChar;
	}
	
	ChatCode(@Nullable String colorCode, String langName) {
		this.colorCode = colorCode;
		this.langName = langName;
		this.hasParam = false;
	}
	
	ChatCode(String colorCode, char colorChar) {
		this.colorCode = colorCode;
		this.langName = colorCode;
		this.hasParam = false;
		this.colorChar = colorChar;
	}
	
	ChatCode(boolean hasParam) {
		this.hasParam = hasParam;
		this.langName = this.name(); // Default to enum name
	}
	
	ChatCode() {
		this(false);
	}
	
	/**
	 * Updates the component.
	 * @param component Component to update.
	 * @param param String parameter.
	 */
	public void updateComponent(MessageComponent component, String param) {
		
	}
	
	public void updateComponent(MessageComponent component, String param, @Nullable VariableString varParam) {
		updateComponent(component, param);
	}
	
	public boolean nextComponent() {
		return true;
	}
}