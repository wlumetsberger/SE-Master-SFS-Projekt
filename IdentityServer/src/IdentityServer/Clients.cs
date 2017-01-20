using IdentityServer4;
using IdentityServer4.Models;
using System.Collections.Generic;

internal class Clients
{
    public static string GetBaseUri(bool isDevelopment)
    {
        if (isDevelopment)
        {
            return "http://localhost:8080";
        }

        return "https://sfsoauthdemo.azurewebsites.net";
    }
    
    public static IEnumerable<Client> Get(bool isDevelopment)
    {
        return new List<Client> {
            new Client {
                ClientId = "sfsclient",
                ClientName = "SFS Application",
                AllowedGrantTypes = GrantTypes.Code,
                ClientSecrets = new List<Secret>{ new Secret(value: "sfsclient".Sha256()) },
                AllowedScopes = new List<string>
                {
                    IdentityServerConstants.StandardScopes.OpenId,
                    IdentityServerConstants.StandardScopes.Profile,
                    IdentityServerConstants.StandardScopes.Email,
                    "role"
                },
                RedirectUris = new List<string> {GetBaseUri(isDevelopment)+"/login/identityserver"},
                PostLogoutRedirectUris = new List<string> {GetBaseUri(isDevelopment)}
            }
        };
    }
}