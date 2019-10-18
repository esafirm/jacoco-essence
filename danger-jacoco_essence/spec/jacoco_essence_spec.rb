require File.expand_path("../spec_helper", __FILE__)

module Danger
  describe Danger::DangerJacocoEssence do
    it "should be a plugin" do
      expect(Danger::DangerJacocoEssence.new(nil)).to be_a Danger::Plugin
    end

    #
    # You should test your custom attributes and methods here
    #
    describe "with Dangerfile" do
      before do
        @dangerfile = testing_dangerfile
        @jplugin = @dangerfile.jacoco_essence

        # mock the PR data
        # you can then use this, eg. github.pr_author, later in the spec
        # json = File.read(File.dirname(__FILE__) + '/support/fixtures/github_pr.json') # example json: `curl https://api.github.com/repos/danger/danger-plugin-template/pulls/18 > github_pr.json`
        # allow(@jplugin.github).to receive(:pr_json).and_return(json)
      end

      it "Run the JAR" do 
        xmlReport = "#{File.dirname(__FILE__)}/fixtures/jacoco.xml"
        @jplugin.report(xmlReport)

        expect(@dangerfile.status_report[:markdowns][0].message).to include("Project Coverage:")
        expect(@dangerfile.status_report[:markdowns][0].message).to include("Coverage: 0.31289113 %")
        expect(@dangerfile.status_report[:markdowns][0].message).to include("Status: :white_check_mark:")
      end

    end
  end
end
