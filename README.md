# Welcome Message Plugin
This is a simple welcome message plugin/mod for paper or fabric servers.
Customise a message, with colour codes or the players name, that will be displayed to your players every time they join

## Usage:
- Simply drag and drop the jar into your plugins folder.
- Modify config.yml (Paper) or config/welcome-message/config.json to customise your message.
- Use %player% as a placeholder for the players name.
- Use &<minecraft-format-code> to specify a color. 
- If you are using the paper version run /welcome-message-reload or restart your server to apply changes, If you are using the fabric version you must restart the server to apply the changes.

## Commands (Only on Paper Version):
- Get - Gets the current welcome message:
```
/welcome-message-get
```

- Set - Sets the welcome message:
```
/welcome-message-set <value>
```

- Reload - Reloads the config (use after editing the config.yml):

```
/welcome-message-reload
```
