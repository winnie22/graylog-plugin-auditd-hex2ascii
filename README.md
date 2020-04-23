# graylog-plugin-auditd-hex2ascii
Graylog plugin for converting hex-encoded string used in auditd logs into human readable format

Installation
------------

[Download the plugin](https://github.com/winnie22/graylog-plugin-auditd-hex2ascii/releases)
and place the `.jar` file in your Graylog plugin directory. The plugin directory
is the `plugins/` folder relative from your `graylog-server` directory by default
and can be configured in your `graylog.conf` file.

Restart `graylog-server` and you are done.

Usage
-----
Parse auditd logs ie. as described [here](https://www.graylog.org/post/back-to-basics-working-with-linux-audit-daemon-log-file).
Create rule that convert hex-encoded string from cmd or proctitle field into human readable format.

```
rule "proctitle2ascii_auditd"
when
 has_field("auditd_proctitle")
then
 set_field("auditd_proctitle_ascii", hex2ascii(to_string($message.auditd_proctitle)));
end
```

Will result into fields:
```
auditd_proctitle: 636174002F6574632F7373682F737368645F636F6E666967
auditd_proctitle_ascii: cat /etc/ssh/sshd_config
```
