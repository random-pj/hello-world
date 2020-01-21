using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Hosting;
using Microsoft.Extensions.Configuration;
using Microsoft.Extensions.Hosting;
using Microsoft.Extensions.Logging;


// Running 10s test @ http://localhost:5000/weatherforecast
// 10 connections

// ┌─────────┬──────┬──────┬───────┬──────┬─────────┬─────────┬──────────┐
// │ Stat    │ 2.5% │ 50%  │ 97.5% │ 99%  │ Avg     │ Stdev   │ Max      │
// ├─────────┼──────┼──────┼───────┼──────┼─────────┼─────────┼──────────┤
// │ Latency │ 0 ms │ 0 ms │ 0 ms  │ 0 ms │ 0.01 ms │ 0.06 ms │ 12.71 ms │
// └─────────┴──────┴──────┴───────┴──────┴─────────┴─────────┴──────────┘
// ┌───────────┬─────────┬─────────┬─────────┬────────┬──────────┬─────────┬─────────┐
// │ Stat      │ 1%      │ 2.5%    │ 50%     │ 97.5%  │ Avg      │ Stdev   │ Min     │
// ├───────────┼─────────┼─────────┼─────────┼────────┼──────────┼─────────┼─────────┤
// │ Req/Sec   │ 47935   │ 47935   │ 56159   │ 57567  │ 55713.46 │ 2570.65 │ 47910   │
// ├───────────┼─────────┼─────────┼─────────┼────────┼──────────┼─────────┼─────────┤
// │ Bytes/Sec │ 7.57 MB │ 7.57 MB │ 8.87 MB │ 9.1 MB │ 8.8 MB   │ 406 kB  │ 7.57 MB │
// └───────────┴─────────┴─────────┴─────────┴────────┴──────────┴─────────┴─────────┘

// Req/Bytes counts sampled once per second.

// 0 2xx responses, 612870 non 2xx responses
// 613k requests in 11.07s, 96.8 MB read

namespace cs
{
    public class Program
    {
        public static void Main(string[] args)
        {
            CreateHostBuilder(args).Build().Run();
        }

        public static IHostBuilder CreateHostBuilder(string[] args) =>
            Host.CreateDefaultBuilder(args)
                .ConfigureWebHostDefaults(webBuilder =>
                {
                    webBuilder.UseStartup<Startup>();
                });
    }
}
