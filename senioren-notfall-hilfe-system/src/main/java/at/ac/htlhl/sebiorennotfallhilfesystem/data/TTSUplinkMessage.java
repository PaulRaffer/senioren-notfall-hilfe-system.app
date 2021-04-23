package at.ac.htlhl.sebiorennotfallhilfesystem.data;

// https://thethingsindustries.com/docs/reference/data-formats/
public class TTSUplinkMessage<Payload>
        implements UplinkMessageI<Payload> {

    public static class EndDeviceIds {
        String device_id;
        private static class ApplicationIds {
            String application_id;

            public String getApplication_id()
            {
                return application_id;
            }
            public void setApplication_id(String application_id)
            {
                this.application_id = application_id;
            }
        }
        ApplicationIds application_ids;
        String dev_eui;
        String join_eui;
        String dev_addr;

        public String getDevice_id()
        {
            return device_id;
        }
        public void setDevice_id(String device_id)
        {
            this.device_id = device_id;
        }

        public ApplicationIds getApplication_ids()
        {
            return application_ids;
        }
        public void setApplication_ids(ApplicationIds application_ids)
        {
            this.application_ids = application_ids;
        }

        public String getDev_eui()
        {
            return dev_eui;
        }
        public void setDev_eui(String dev_eui)
        {
            this.dev_eui = dev_eui;
        }

        public String getJoin_eui()
        {
            return join_eui;
        }
        public void setJoin_eui(String join_eui)
        {
            this.join_eui = join_eui;
        }

        public String getDev_addr()
        {
            return dev_addr;
        }
        public void setDev_addr(String dev_addr)
        {
            this.dev_addr = dev_addr;
        }
    }
    EndDeviceIds end_device_ids;
    String[] correlation_ids;
    String received_at;
    private static class UplinkMessage<Payload> {

        String session_key_id;
        int f_cnt;
        int f_port;
        String frm_payload;
        Payload decoded_payload;
        private static class Metadata {
            private static class GatewayIds {
                String gateway_id;
                String eui;

                public String getGateway_id()
                {
                    return gateway_id;
                }
                public void setGateway_id(String gateway_id)
                {
                    this.gateway_id = gateway_id;
                }

                public String getEui()
                {
                    return eui;
                }
                public void setEui(String eui)
                {
                    this.eui = eui;
                }
            }
            GatewayIds gateway_ids;
            String time;
            long timestamp;
            int rssi;
            int channel_rssi;
            int snr;
            String uplink_token;
            int channel_index;

            public GatewayIds getGateway_ids()
            {
                return gateway_ids;
            }
            public void setGateway_ids(GatewayIds gateway_ids)
            {
                this.gateway_ids = gateway_ids;
            }

            public String getTime()
            {
                return time;
            }
            public void setTime(String time)
            {
                this.time = time;
            }

            public long getTimestamp()
            {
                return timestamp;
            }
            public void setTimestamp(long timestamp)
            {
                this.timestamp = timestamp;
            }

            public int getRssi()
            {
                return rssi;
            }
            public void setRssi(int rssi)
            {
                this.rssi = rssi;
            }

            public int getChannel_rssi()
            {
                return channel_rssi;
            }
            public void setChannel_rssi(int channel_rssi)
            {
                this.channel_rssi = channel_rssi;
            }

            public int getSnr()
            {
                return snr;
            }
            public void setSnr(int snr)
            {
                this.snr = snr;
            }

            public String getUplink_token()
            {
                return uplink_token;
            }
            public void setUplink_token(String uplink_token)
            {
                this.uplink_token = uplink_token;
            }

            public int getChannel_index()
            {
                return channel_index;
            }
            public void setChannel_index(int channel_index)
            {
                this.channel_index = channel_index;
            }
        }
        Metadata[] rx_metadata;
        private static class Settings {
            private static class DataRate {
                private static class Lora {
                    int bandwidth;
                    int spreading_factor;

                    public int getBandwidth()
                    {
                        return bandwidth;
                    }
                    public void setBandwidth(int bandwidth)
                    {
                        this.bandwidth = bandwidth;
                    }

                    public int getSpreading_factor()
                    {
                        return spreading_factor;
                    }
                    public void setSpreading_factor(int spreading_factor)
                    {
                        this.spreading_factor = spreading_factor;
                    }
                }
                Lora lora;

                public Lora getLora()
                {
                    return lora;
                }
                public void setLora(Lora lora)
                {
                    this.lora = lora;
                }
            }
            DataRate data_rate;
            int data_rate_index;
            String coding_rate;
            String frequency;
            long timestamp;
            String time;

            public DataRate getData_rate()
            {
                return data_rate;
            }
            public void setData_rate(DataRate data_rate)
            {
                this.data_rate = data_rate;
            }

            public int getData_rate_index()
            {
                return data_rate_index;
            }
            public void setData_rate_index(int data_rate_index)
            {
                this.data_rate_index = data_rate_index;
            }

            public String getCoding_rate()
            {
                return coding_rate;
            }
            public void setCoding_rate(String coding_rate)
            {
                this.coding_rate = coding_rate;
            }

            public String getFrequency()
            {
                return frequency;
            }
            public void setFrequency(String frequency)
            {
                this.frequency = frequency;
            }

            public long getTimestamp()
            {
                return timestamp;
            }
            public void setTimestamp(long timestamp)
            {
                this.timestamp = timestamp;
            }

            public String getTime()
            {
                return time;
            }
            public void setTime(String time)
            {
                this.time = time;
            }
        }
        Settings settings;
        String received_at;
        String consumed_airtime;
        private static class Locations {
            private static class User extends Location<Double> {
                String source;

                public String getSource()
                {
                    return source;
                }
                public void setSource(String source)
                {
                    this.source = source;
                }
            }
            User user;

            public User getUser()
            {
                return user;
            }
            public void setUser(User user)
            {
                this.user = user;
            }
        }
        Locations locations;

        public String getSession_key_id()
        {
            return session_key_id;
        }
        public void setSession_key_id(String session_key_id)
        {
            this.session_key_id = session_key_id;
        }

        public int getF_cnt()
        {
            return f_cnt;
        }
        public void setF_cnt(int f_cnt)
        {
            this.f_cnt = f_cnt;
        }

        public int getF_port()
        {
            return f_port;
        }
        public void setF_port(int f_port)
        {
            this.f_port = f_port;
        }

        public String getFrm_payload()
        {
            return frm_payload;
        }
        public void setFrm_payload(String frm_payload)
        {
            this.frm_payload = frm_payload;
        }

        public Payload getDecoded_payload()
        {
            return decoded_payload;
        }
        public void setDecoded_payload(Payload decoded_payload)
        {
            this.decoded_payload = decoded_payload;
        }

        public Metadata[] getRx_metadata()
        {
            return rx_metadata;
        }
        public void setRx_metadata(Metadata[] rx_metadata)
        {
            this.rx_metadata = rx_metadata;
        }

        public Settings getSettings()
        {
            return settings;
        }
        public void setSettings(Settings settings)
        {
            this.settings = settings;
        }

        public String getReceived_at()
        {
            return received_at;
        }
        public void setReceived_at(String received_at)
        {
            this.received_at = received_at;
        }

        public String getConsumed_airtime()
        {
            return consumed_airtime;
        }
        public void setConsumed_airtime(String consumed_airtime)
        {
            this.consumed_airtime = consumed_airtime;
        }

        public Locations getLocations()
        {
            return locations;
        }
        public void setLocations(Locations locations)
        {
            this.locations = locations;
        }
    }
    UplinkMessage<Payload> uplink_message;
    boolean simulated;

    public void set(final TTSUplinkMessage<Payload> message)
    {
        this.end_device_ids = message.end_device_ids;
        this.correlation_ids = message.correlation_ids;
        this.received_at = message.received_at;
        this.uplink_message = message.uplink_message;
        this.simulated = message.simulated;
    }

    public EndDeviceIds getEnd_device_ids()
    {
        return end_device_ids;
    }
    @Override
    public String getDevice_id() {
        return getEnd_device_ids().getDevice_id();
    }
    public void setEnd_device_ids(EndDeviceIds end_device_ids)
    {
        this.end_device_ids = end_device_ids;
    }

    public String[] getCorrelation_ids()
    {
        return correlation_ids;
    }
    public void setCorrelation_ids(String[] correlation_ids)
    {
        this.correlation_ids = correlation_ids;
    }

    public String getReceived_at()
    {
        return received_at;
    }
    public void setReceived_at(String received_at)
    {
        this.received_at = received_at;
    }

    public UplinkMessage<Payload> getUplink_message()
    {
        return uplink_message;
    }
    @Override
    public Payload getPayload()
    {
        return getUplink_message().getDecoded_payload();
    }

    public void setUplink_message(UplinkMessage<Payload> uplink_message)
    {
        this.uplink_message = uplink_message;
    }

    public boolean isSimulated()
    {
        return simulated;
    }
    public void setSimulated(boolean simulated)
    {
        this.simulated = simulated;
    }

}
