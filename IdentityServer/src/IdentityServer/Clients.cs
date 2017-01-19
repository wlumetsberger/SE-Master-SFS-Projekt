using IdentityServer4;
using IdentityServer4.Models;
using System.Collections.Generic;

internal class Clients
{
    public static IEnumerable<Client> Get()
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
                RedirectUris = new List<string> {"http://localhost:8080/login/identityserver"},
                PostLogoutRedirectUris = new List<string> {"https://localhost:8080"}
            }
        };
    }
}