package cz.etnetera;

import org.graylog2.plugin.PluginMetaData;
import org.graylog2.plugin.ServerStatus;
import org.graylog2.plugin.Version;

import java.net.URI;
import java.util.Collections;
import java.util.Set;

/**
 * Implement the PluginMetaData interface here.
 */
public class AuditdHex2AsciiMetaData implements PluginMetaData {
    private static final String PLUGIN_PROPERTIES = "cz.etnetera.graylog-plugin-auditd-hex2ascii/graylog-plugin.properties";

    @Override
    public String getUniqueId() {
        return "cz.etnetera.AuditdHex2AsciiPlugin";
    }

    @Override
    public String getName() {
        return "AuditdHex2Ascii";
    }

    @Override
    public String getAuthor() {
        return "Petr Medonos <petr.medonos@etnetera.cz>";
    }

    @Override
    public URI getURL() {
        return URI.create("https://github.com/winnie22/graylog-plugin-auditd-hex2ascii");
    }

    @Override
    public Version getVersion() {
        return Version.fromPluginProperties(getClass(), PLUGIN_PROPERTIES, "version", Version.from(0, 0, 0, "unknown"));
    }

    @Override
    public String getDescription() {
        // TODO Insert correct plugin description
        return "Description of AuditdHex2Ascii plugin";
    }

    @Override
    public Version getRequiredVersion() {
        return Version.fromPluginProperties(getClass(), PLUGIN_PROPERTIES, "graylog.version", Version.from(0, 0, 0, "unknown"));
    }

    @Override
    public Set<ServerStatus.Capability> getRequiredCapabilities() {
        return Collections.emptySet();
    }
}
