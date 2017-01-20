using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Builder;
using Microsoft.AspNetCore.Hosting;
using Microsoft.AspNetCore.Http;
using Microsoft.Extensions.DependencyInjection;
using Microsoft.Extensions.Logging;
using IdentityServer4.Models;

namespace IdentityServer
{
    public class Startup
    {

        public IHostingEnvironment Env { get; set; }

        public Startup(IHostingEnvironment env)
        {
            Env = env;
        }

        // This method gets called by the runtime. Use this method to add services to the container.
        // For more information on how to configure your application, visit https://go.microsoft.com/fwlink/?LinkID=398940
        public void ConfigureServices(IServiceCollection services)
        {
            services.AddMvc();

            services.AddIdentityServer()
            .AddInMemoryClients(Clients.Get(Env.IsDevelopment()))
            .AddInMemoryIdentityResources(Resources.GetIdentityResources())
            .AddTestUsers(Users.Get())
            .AddTemporarySigningCredential();
        }

        // This method gets called by the runtime. Use this method to configure the HTTP request pipeline.
        public void Configure(IApplicationBuilder app, IHostingEnvironment env, ILoggerFactory loggerFactory)
        {
            loggerFactory.AddConsole();

            if (env.IsDevelopment())
            {
                app.UseDeveloperExceptionPage();
            }

            app.Use(async (context, next) =>
            {
                if (context.Request.IsHttps || env.IsDevelopment())
                {
                    await next();
                }
                else
                {
                    var httpsUrl = "https://" + context.Request.Host + context.Request.Path;
                    context.Response.Redirect(httpsUrl);
                }
            });
            app.UseIdentityServer();
            app.UseStaticFiles();
            app.UseMvcWithDefaultRoute();
            

        }
    }
}
